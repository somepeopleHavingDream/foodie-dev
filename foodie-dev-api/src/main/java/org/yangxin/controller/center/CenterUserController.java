package org.yangxin.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yangxin.pojo.User;
import org.yangxin.pojo.query.center.CenterUserBO;
import org.yangxin.pojo.vo.common.JSONVO;
import org.yangxin.pojo.vo.user.UserVO;
import org.yangxin.service.center.CenterUserService;
import org.yangxin.utils.CookieUtil;
import org.yangxin.utils.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.yangxin.controller.BaseController.IMAGE_USER_FACE_LOCATION;

/**
 * @author yangxin
 * 2020/11/25 15:07
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
@Api(value = "用户信息接口", tags = {"用户信息相关接口"})
@RestController
@RequestMapping("userInfo")
@Slf4j
public class CenterUserController {

    private final CenterUserService centerUserService;

    @Autowired
    public CenterUserController(CenterUserService centerUserService) {
        this.centerUserService = centerUserService;
    }

    @ApiOperation(value = "用户头像修改", notes = "用户头像修改", httpMethod = "POST")
    @PostMapping("/updateFace")
    public JSONVO updateFace(@ApiParam(name = "userId", value = "用户id", required = true)
                         @RequestParam String userId,
                         @ApiParam(name = "file", value = "用户头像", required = true)
                                 MultipartFile file,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        // 在路径上为每一个用户增加一个userId，用于区分不同用户上传
        String uploadPathPrefix = File.separator + userId;

        // 开始文件上传
        if (file != null) {
            // 获得文件上传的文件名称
            String originalFilename = file.getOriginalFilename();
            if (StringUtils.isNotBlank(originalFilename)) {
                // 文件重命名 mooc-face.png -> ["mooc-face", "png"]
                String[] fileNameArr = originalFilename.split("\\.");

                // 获取文件的后缀名
                String suffix = fileNameArr[fileNameArr.length - 1];

                // face-{userid}.png
                // 文件名称重组，覆盖式上传,增量式：额外拼接当前时间
                String newFileName = "face-" + userId + "." + suffix;

                // 上传的头像最终保存的位置
                String finalFacePath = IMAGE_USER_FACE_LOCATION + uploadPathPrefix + File.separator + newFileName;

                File outFile = new File(finalFacePath);
                if (outFile.getParentFile() != null) {
                    // 创建文件夹
                    outFile.getParentFile().mkdirs();
                }

                // 文件输出保存到目录
                try(FileOutputStream fileOutputStream = new FileOutputStream(outFile)) {
                    IOUtils.copy(file.getInputStream(), fileOutputStream);
                } catch (IOException e) {
                    log.error("文件保存失败！", e);
                }
            }
        } else {
            return JSONVO.errorMsg("文件不能为空！");
        }
        return JSONVO.ok();
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "POST")
    @PostMapping("/update")
    public JSONVO update(@ApiParam(name = "userId", value = "用户id", required = true)
                         @RequestParam String userId,
                         @RequestBody @Valid CenterUserBO centerUserBO,
                         HttpServletRequest request,
                         HttpServletResponse response,
                         BindingResult bindingResult) {
        // 判断BindingResult是否保存错误的验证信息，如果有，则直接return
        if (bindingResult.hasErrors()) {
            return JSONVO.errorMap(getErrorMap(bindingResult));
        }

        User user = centerUserService.updateUserInfo(userId, centerUserBO);

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);

        CookieUtil.setCookie(request, response, "user", JSONUtil.obj2String(userVO), true);

        // todo 后续要改，增加令牌token，会整合进redis，分布式会话

        return JSONVO.ok();
    }

    private Map<String, String> getErrorMap(BindingResult bindingResult) {
        Map<String, String> map = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            // 发生验证错误所对应的某一个属性
            String field = fieldError.getField();
            // 验证错误的信息
            String defaultMessage = fieldError.getDefaultMessage();

            map.put(field, defaultMessage);
        }

        return map;
    }
}
