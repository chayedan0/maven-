/**
 * 
 */
package com.yangguangyu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yangguangyu.entity.Article;

/**
 * @author 杨光宇
 * 2019年11月21日
 */
public interface ArticleMapper {

	/**
	 * @param id
	 * @return
	 */
	Article getDetailById(int id);

	/**
	 * @param article
	 * @return
	 */
	@Insert("INSERT INTO cms_article("
			+ " title,content,picture,channel_id,category_id,"
			+ " user_id,hits,hot,status,deleted,"
			+ " created,updated,commentCnt,articleType) "
			+ " values("
			+ " #{title},#{content},#{picture},#{channelId},#{categoryId},"
			+ "#{userId},#{hits},#{hot},#{status},#{deleted},"
			+ "now(),now(),#{commentCnt},#{articleType})")
	int add(Article article);

	/**
	 * @param userId
	 * @return
	 */
	List<Article> listByUser(Integer userId);

	/**
	 * @param id
	 * @return
	 */
	@Select("SELECT id, title,user_id AS userId FROM cms_article WHERE id = #{value}")
	@ResultType(Article.class)
	Article checkExist(int id);

	/**
	 * @param id
	 * @return
	 */
	@Update(" UPDATE cms_article SET  deleted=1 WHERE id=#{value} ")
	int delete(int id);

	/**
	 * @param chnId
	 * @param categoryId
	 * @return
	 */
	List<Article> listByCat(@Param("chnId")int chnId, @Param("categoryId")int categoryId);

	/**
	 * @return
	 */
	List<Article> hotList();

	/**
	 * @param i
	 * @return
	 */
	List<Article> newList(int i);

	/**
	 * @param status
	 * @return
	 */
	List<Article> listByStatus(int status);

	@Update(" UPDATE cms_article SET  status=#{status} "
			+ " WHERE id=#{id} ")
	int apply(@Param("id") int id,@Param("status") int status);

	/**
	 * 设置热门
	 * @param id
	 * @param status
	 * @return
	 */
	@Update(" UPDATE cms_article SET  hot=#{status} "
			+ " WHERE id=#{id} ")
	int setHot(@Param("id") int id,@Param("status") int status);

	/**
	 * @param id
	 * @return
	 */
	Article getById(Integer id);

	/**
	 * @param article
	 * @return
	 */
	@Update("UPDATE cms_article SET title=#{title},content=#{content},"
			+ "picture=#{picture},channel_id=#{channelId},"
			+ "category_id=#{categoryId},status=0,updated=now() WHERE id=#{id}")
	int update(Article article);

}
