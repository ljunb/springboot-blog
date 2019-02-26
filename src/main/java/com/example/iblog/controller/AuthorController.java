package com.example.iblog.controller;

import com.example.iblog.common.ConvertUtils;
import com.example.iblog.common.PageResponseResult;
import com.example.iblog.common.ResponseResult;
import com.example.iblog.common.ResponseCodeEnum;
import com.example.iblog.domain.Article;
import com.example.iblog.domain.Author;
import com.example.iblog.domain.Comment;
import com.example.iblog.services.impl.AuthorServiceImpl;
import com.example.iblog.vo.AuthorVO;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@Api("作者APIs")
@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorServiceImpl authorService;

    @ApiOperation("获取作者列表")
    @GetMapping
    public ResponseResult<List<AuthorVO>> getAuthorList() {
        try {
            List<Author> authors = authorService.getAuthorList();
            List<AuthorVO> authorVOs = ConvertUtils.convertFromList(authors, AuthorVO.class);
            for (AuthorVO authorVO: authorVOs) {
                for (Author author: authors) {
                    if (authorVO.getAuthorId().longValue() == author.getAuthorId().longValue()) {
                        authorVO.setSexName(ConvertUtils.convertSex(author.getSex()));
                        break;
                    }
                }
            }
            return new ResponseResult<>(authorVOs, ResponseCodeEnum.SERVICE_OK);
        } catch (Exception e) {
            return new ResponseResult<>(ResponseCodeEnum.SERVICE_ERROR.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "获取作者信息", notes = "根据authorId获取用户信息")
    @ApiImplicitParam(name = "authorId", value = "作者id", required = true, dataType = "number")
    @GetMapping("/{authorId}")
    public ResponseResult<AuthorVO> getAuthor(@PathVariable BigInteger authorId) {
        try {
            Author author = authorService.getAuthor(authorId);
            AuthorVO authorVO = ConvertUtils.convertFromObject(author, AuthorVO.class);
            if (authorVO != null) {
                authorVO.setSexName(ConvertUtils.convertSex(author.getSex()));
                return new ResponseResult<>(authorVO, ResponseCodeEnum.SERVICE_OK);
            } else {
                return new ResponseResult<>(ResponseCodeEnum.RESOURCE_NOT_FOUNDED_ERROR);
            }
        } catch (Exception e) {
            return new ResponseResult<>(ResponseCodeEnum.SERVICE_ERROR.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "添加作者", notes = "根据Author对象创建作者")
    @ApiImplicitParam(name = "author", value = "作者对象实体", required = true, dataType = "Author")
    @PostMapping("/create")
    public ResponseResult<Author> createAuthor(@Valid @RequestBody Author author) {
        try {
            int resultCode = authorService.createAuthor(author);
            if (resultCode > 0) {
                return new ResponseResult<>(author, ResponseCodeEnum.SERVICE_OK);
            } else {
                return new ResponseResult<>(ResponseCodeEnum.INSERT_RESOURCE_ERROR);
            }
        } catch (Exception e) {
            return new ResponseResult<>(ResponseCodeEnum.SERVICE_ERROR.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "获取文章列表", notes = "根据authorId获取该作者下的所有文章")
    @ApiImplicitParam(name = "authorId", value = "作者id", required = true, dataType = "number")
    @GetMapping("/{authorId}/articleList")
    public PageResponseResult<Article> getArticleListByAuthorId(
            @PathVariable BigInteger authorId,
            @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articleList = authorService.getArticleList(authorId);
        return new PageResponseResult<>(articleList);
    }

    @ApiOperation(value = "更新作者信息", notes = "根据Author实体更新作者信息")
    @ApiImplicitParam(name = "author", value = "作者对象实体", required = true, dataType = "Author")
    @PutMapping("/modify")
    public ResponseResult modifyAuthor(@RequestBody Author author) {
        try {
            int resultCode = authorService.modifyAuthor(author);
            if (resultCode > 0) {
                return new ResponseResult<>(author, ResponseCodeEnum.SERVICE_OK);
            } else {
                return new ResponseResult<>(ResponseCodeEnum.MODIFY_RESOURCE_ERROR);
            }
        } catch (Exception e) {
            return new ResponseResult<>(ResponseCodeEnum.SERVICE_ERROR.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "删除作者", notes = "根据authorId删除指定作者")
    @ApiImplicitParam(name = "authorId", value = "作者id", required = true, dataType = "number")
    @DeleteMapping("/{authorId}")
    public ResponseResult<BigInteger> removeAuthor(@PathVariable BigInteger authorId) {
        try {
            int resultCode = authorService.removeAuthorById(authorId);
            if (resultCode > 0) {
                return new ResponseResult<>(authorId, ResponseCodeEnum.SERVICE_OK);
            } else {
                return new ResponseResult<>(ResponseCodeEnum.REMOVE_RESOURCE_ERROR);
            }
        } catch (Exception e) {
            return new ResponseResult<>(ResponseCodeEnum.SERVICE_ERROR.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "获取作者评论列表", notes = "根据authorId获取该作者的所有评论")
    @ApiImplicitParam(name = "authorId", value = "作者id", required = true, dataType = "number")
    @GetMapping("/{authorId}/commentList")
    public ResponseResult<List<Comment>> getArticleListByAuthorId(@PathVariable BigInteger authorId) {
        try {
            List<Comment> commentList = authorService.getCommentList(authorId);
            return new ResponseResult<>(commentList, ResponseCodeEnum.SERVICE_OK);
        } catch (Exception e) {
            return new ResponseResult<>(ResponseCodeEnum.SERVICE_ERROR.getCode(), e.getMessage());
        }
    }
}
