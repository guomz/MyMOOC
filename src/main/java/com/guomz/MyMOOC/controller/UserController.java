package com.guomz.MyMOOC.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guomz.MyMOOC.entity.Article;
import com.guomz.MyMOOC.entity.Comment;
import com.guomz.MyMOOC.entity.Course;
import com.guomz.MyMOOC.entity.CourseHistory;
import com.guomz.MyMOOC.entity.Movie;
import com.guomz.MyMOOC.entity.Teacher;
import com.guomz.MyMOOC.entity.UserInfo;
import com.guomz.MyMOOC.service.ArticleService;
import com.guomz.MyMOOC.service.CommentService;
import com.guomz.MyMOOC.service.CourseService;
import com.guomz.MyMOOC.service.MovieService;
import com.guomz.MyMOOC.service.TeacherService;

/**
 * 个人信息页面
 * 
 * @author 12587
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private MovieService movieService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CommentService commentService;

	/**
	 * 添加用户的学习记录，并返回此课程的详细信息、视频列表、上次观看的视频信息、文章列表、上次观看的文章
	 * 
	 * @param userId
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value = "/addcoursetouser", method = RequestMethod.GET)
	public Map<String, Object> addCourseToUser(@RequestParam(value = "userId", required = false) Long userId,
			@RequestParam(value = "courseId", required = false) Long courseId) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			CourseHistory courseHistory = courseService.handleHistory(userId, courseId);
			//获取当前课程信息
			Course course=courseService.getCourseById(courseHistory.getCourseId());
			//获取讲师信息
			Teacher teacher=teacherService.getTeacherById(course.getTeacherId());
			//获取是否被收藏
			boolean isCollected = courseService.isCollected(userId, courseId);
			//获取是否评论过该课程
			Boolean isComment=false;
			Comment comment=new Comment();
			UserInfo user=new UserInfo();
			user.setUserId(userId);
			comment.setCourseId(courseId);
			comment.setCourseId(courseId);
			List<Comment> commentList = commentService.getCommentList(comment);
			if(commentList.size()>0)
			{
				isComment=true;
			}
			else
			{
				isComment=false;
			}
			//获取当前课程当前章节的文章列表
			Article article=new Article();
			article.setCourseId(courseHistory.getCourseId());
			article.setChapter(courseHistory.getChapter());
			List<Article> articleList=articleService.getArticleByCondition(article);
			if(articleList.size()==0)
			{
				articleList=null;
			}
			//获取该课程视频列表
			Movie movie=new Movie();
			Movie currentMovie=null;
			Double currentPos=null;
			movie.setCourseId(courseHistory.getCourseId());
			List<Movie> movieList=movieService.getMovieByCondition(movie);
			if(movieList.size()==0)
			{
				movieList=null;
			}
			else
			{
				//获取当前进度的视频
				movie.setChapter(courseHistory.getChapter());
				currentMovie=movieService.getMovieByCondition(movie).get(0);
				currentPos=courseHistory.getCurrentPos();
			}
			
			modelMap.put("success", true);
			modelMap.put("course", course);
			modelMap.put("teacher", teacher);
			modelMap.put("isCollected", isCollected);
			modelMap.put("isComment", isComment);
			modelMap.put("commentList", commentList);
			modelMap.put("articleList", articleList);
			modelMap.put("movieList", movieList);
			modelMap.put("currentMovie", currentMovie);
			modelMap.put("currentPos", currentPos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
		}
		return modelMap;
	}

	/**
	 * 获得用户学习过的课程记录与收藏的课程列表
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getmycourselist", method = RequestMethod.GET)
	public Map<String, Object> getMyCourseList(@RequestParam(value = "userId", required = false) Long userId) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			List<Course> courseList = courseService.getCourseByUserId(userId);
			List<Course> collectionList = courseService.getCollectionList(userId);
			modelMap.put("success", true);
			modelMap.put("courseList", courseList);
			modelMap.put("collectionList", collectionList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}

		return modelMap;
	}

	/**
	 * 更新用户观看视频历史
	 * 
	 * @param userId
	 * @param courseId
	 * @param movieId
	 * @return
	 */
	@RequestMapping(value = "/updatehistory", method = RequestMethod.GET)
	public Map<String, Object> updateHistory(CourseHistory courseHistory) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			courseService.updateHistory(courseHistory);
			modelMap.put("success", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
		}
		return modelMap;
	}

	/**
	 * 获取当前章节的文章列表
	 * 
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value = "/getarticlelist", method = RequestMethod.GET)
	public Map<String, Object> getArticleList(@RequestParam(value="courseId" ,required=false)Long courseId, 
			@RequestParam(value="chapter",required=false)String chapter) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Article article=new Article();
		article.setCourseId(courseId);
		article.setChapter(chapter);
		try {
			List<Article> articleList = articleService.getArticleByCondition(article);
			modelMap.put("articleList", articleList);
			modelMap.put("success", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
		}
		
		return modelMap;
	}
	
	/**
	 * 处理收藏课程请求
	 * @return
	 */
	@RequestMapping(value="/handlecollection",method=RequestMethod.GET)
	public Map<String ,Object> handleCollection(@RequestParam(value="userId",required=false)Long userId,
			@RequestParam(value="courseId",required=false)Long courseId)
	{
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			courseService.handleCollection(userId, courseId);
			modelMap.put("success", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
		}
		
		return modelMap;
	}
	
	/**
	 * 提交评论
	 * @param comment
	 * @return
	 */
	@RequestMapping(value="/submitcomment",method=RequestMethod.GET)
	public Map<String,Object> submitComment(@RequestParam(value="userId",required=false)Long userId,
			@RequestParam(value="courseId",required=false)Long courseId,
			@RequestParam(value="commentContent",required=false)String commentContent,
			@RequestParam(value="commentDate",required=false)String commentDate)
	{
		Comment comment=new Comment();
		comment.setCommentContent(commentContent);
		comment.setCommentDate(commentDate);
		comment.setCourseId(courseId);
		UserInfo user=new UserInfo();
		user.setUserId(userId);
		comment.setUser(user);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			if(commentService.getCommentList(comment).size()==0)
			{
				commentService.insertComment(comment);
				modelMap.put("success", true);
			}
			else
			{
				modelMap.put("success", false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
		}
		return modelMap;
	}
	
	/**
	 * 刷新评论区
	 * @param comment
	 * @return
	 */
	@RequestMapping(value="/reloadcomment",method=RequestMethod.GET)
	public Map<String,Object> reloadComment(Comment comment)
	{
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			List<Comment> commentList = commentService.getCommentList(comment);
			modelMap.put("success", true);
			modelMap.put("commentList", commentList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
		}
		
		return modelMap;
	}
	
	/**
	 * 下载文件测试
	 */
	@RequestMapping(value = "/downloadfile")
	public void downloadFile(HttpServletResponse resp) {
		System.out.println("开始下载");
		String filePath = "d:/example.pdf";
		File f = new File(filePath);

		byte buffer[] = new byte[1024];
		FileInputStream fis = null;
		BufferedInputStream bis = null;

		try {
			fis = new FileInputStream(f);
			bis = new BufferedInputStream(fis);
			OutputStream os = resp.getOutputStream();
			int i = bis.read(buffer);
			while (i != -1) {
				os.write(buffer, 0, i);
				i = bis.read(buffer);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
