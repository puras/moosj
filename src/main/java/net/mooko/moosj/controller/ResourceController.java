package net.mooko.moosj.controller;

import net.mooko.common.json.Converter;
import net.mooko.common.json.Response;
import net.mooko.common.json.ResponseHelper;
import net.mooko.moosj.service.MultipartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * @author puras <he@puras.me>
 * @since 15/8/24  下午11:37
 */
@Controller
@RequestMapping("resources")
public class ResourceController {

    @Autowired
    private Converter jsonConverter;

    @Autowired
    private MultipartService multipartService;

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public void upload(MultipartRequest multipartRequest, HttpServletResponse response) throws Exception {
        Response resp = ResponseHelper.createSuccessResponse(multipartService.saveFiles(multipartRequest));
        response.setContentType("text/html; charset=UTF-8");
        multipartService.saveFiles(multipartRequest);
        response.getWriter().print(jsonConverter.convertToString(resp));
        response.getWriter().close();
//        return new ResponseEntity<>(jsonConverter.convertToString(resp), HttpStatus.OK);
    }

    @RequestMapping(value = "upload/html", method = RequestMethod.POST)
    public String uploadReturnPage(MultipartRequest multipartRequest, Model model) throws Exception {
        Response resp = ResponseHelper.createSuccessResponse(multipartService.saveFiles(multipartRequest));
        model.addAttribute("resp", resp);
        return "upload";
    }
}
