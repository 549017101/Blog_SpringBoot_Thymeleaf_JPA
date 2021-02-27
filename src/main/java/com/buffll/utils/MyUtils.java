package com.buffll.utils;

import com.buffll.entity.Blog;
import com.buffll.entity.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义工具类
 * @author pxz
 * @create 2021-02-27 16:39
 */
public class MyUtils {
	/**
	 * 将字符串转换成集合<br>
	 * 形如: "1,2,3,4..." 这样的字符串,此方法可以将它转换为一个集合
	 * @param ids
	 * @return
	 */
	public static List<Long> convertToList(String ids) {
		List<Long> list = new ArrayList<>();
		if (!"".equals(ids) && ids != null) {
			String[] idarray = ids.split(",");
			for (int i = 0; i < idarray.length; i++) {
				list.add(new Long(idarray[i]));
			}
		}
		return list;
	}
	
	/**
	 * 将id拼接成字符串<br>
	 * 形如 1 2 3 4 ... 这样的id,此方法可以将其转换为 "1,2,3,4...." 这样的字符串
	 * @param tags
	 * @param blog
	 * @return
	 */
	public static String tagsToIds(List<Tag> tags, Blog blog) {
		if (!tags.isEmpty()) {
			StringBuffer ids = new StringBuffer();
			boolean flag = false;
			for (Tag tag : tags) {
				if (flag) {
					ids.append(",");
				} else {
					flag = true;
				}
				ids.append(tag.getId());
			}
			return ids.toString();
		} else {
			return blog.getTagIds();
		}
	}
}
