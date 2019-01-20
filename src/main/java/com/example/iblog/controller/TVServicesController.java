package com.example.iblog.controller;

import com.example.iblog.dto.TVServiceDto;
import jdk.management.resource.ResourceRequestDeniedException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;
import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/tvservices")
public class TVServicesController {

    @GetMapping()
    public List<TVServiceDto> getAll() {
        List<TVServiceDto> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.OCTOBER, 2, 0, 0);
        list.add(new TVServiceDto(1, "西部世界", 1, calendar.getTime()));
        list.add(new TVServiceDto(2, "权力的游戏", 7, calendar.getTime()));
        list.add(new TVServiceDto(3, "真探", 3, calendar.getTime()));
        list.add(new TVServiceDto(4, "我的天才女友", 3, calendar.getTime()));
        return list;
    }

    @GetMapping("/{id}")
    public TVServiceDto getOne(@PathVariable int id) {
        List<TVServiceDto> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.OCTOBER, 2, 0, 0);
        list.add(new TVServiceDto(1, "西部世界", 1, calendar.getTime()));
        list.add(new TVServiceDto(2, "权力的游戏", 7, calendar.getTime()));
        list.add(new TVServiceDto(3, "真探", 3, calendar.getTime()));
        return list.get(id);
    }

    @PostMapping
    public TVServiceDto insetOne(@Valid @RequestBody TVServiceDto tvServiceDto) {
        tvServiceDto.setOriginRelease(new Date());
        return tvServiceDto;
    }

    @PutMapping("/{id}")
    public TVServiceDto updateOne(@PathVariable int id, TVServiceDto tvServiceDto) {
        return new TVServiceDto(1, "西部世界", 1, new Date());
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteOne(@PathVariable int id, HttpServletRequest request,
                                         @RequestParam(value = "delete_reason", required = false) String deleteReason) throws Exception {
        Map<String, String> result = new HashMap<>();
        if (id == 1) {
            result.put("message", request.getRemoteAddr());
        } else if (id == 2) {
            throw new RuntimeException("2 not allowed delete");
        } else {
            throw new ResourceRequestDeniedException();
        }
        return result;
    }

    @PostMapping(value = "/{id}/photos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addPhoto(@PathVariable int id, @RequestParam("photo") MultipartFile imgFile) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("target/");
        stringBuilder.append(imgFile.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(stringBuilder.toString());
        IOUtils.copy(imgFile.getInputStream(), fileOutputStream);
        fileOutputStream.close();
    }
}
