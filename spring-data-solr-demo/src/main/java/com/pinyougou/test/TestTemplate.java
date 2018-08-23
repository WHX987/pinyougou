package com.pinyougou.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry.Highlight;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.pojo.TbItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-solr.xml")
public class TestTemplate {

	@Autowired
	private SolrTemplate solrTemplate;

	/**
	 * 添加和修改的方法（用的是同一个）
	 */
	@Test
	public void add() {
		List<TbItem> list = new ArrayList<>();
		for (long i = 0; i < 101; i++) {
			
			TbItem item = new TbItem();
			item.setId(i+1L);
			item.setBrand("小米");
			item.setCategory("手机");
			item.setSeller("小米专卖店之"+i);
			item.setPrice(new BigDecimal(1999+i));
			item.setTitle("小米"+i);
			item.setGoodsId(1L);
			list.add(item);
		}
		solrTemplate.saveBeans(list);
		solrTemplate.commit();
		
	}

	/**
	 * 按主键查询
	 */
	@Test
	public void findById() {
		TbItem item = solrTemplate.getById(1, TbItem.class);
		System.out.println(item.getTitle());
	}

	/**
	 * 按主键删除
	 */
	@Test
	public void deleteById() {
		solrTemplate.deleteById("1");
		solrTemplate.commit();
	}

	/**
	 * 分页查询
	 */
	@Test
	public void findPage() {
		Query query = new SimpleQuery("*:*");
		query.setOffset(20); // 设置开始索引，默认的是0；
		query.setRows(20);  // 设置每页记录数，默认是10
		ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
		System.out.println("总记录数: "+page.getTotalElements());
		List<TbItem> content = page.getContent();
		showList(content);
	}
	
	// 显示记录数据
	private void showList(List<TbItem> content) {
		for (TbItem item : content) {
			System.out.println(item.getTitle()+" : "+item.getPrice());
		}
		
	}
	
	/**
	 * 条件查询
	 */
	@Test
	public void pageQueryMutil(){
		Query query = new SimpleQuery("*:*");
		Criteria criteria = new Criteria("item_title").contains("2");
		
		criteria = criteria.and("item_title").contains("5");
		
		query.addCriteria(criteria);
		
		ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
		
		System.out.println("总记录数: "+page.getTotalElements());
		List<TbItem> content = page.getContent();
		showList(content);
	}
	
	/**
	 * 高亮查询
	 */
	@Test
	public void simpleHighlightQuery() {
		// 高亮查询对象
		SimpleHighlightQuery query = new SimpleHighlightQuery();
		// 条件查询
		Criteria criteria = new Criteria("item_title").is("番茄");
		// and条件
		criteria.contains("19");
		// 将条件添加到查询对象中
		query.addCriteria(criteria);
		// 创建高亮对象，添加高亮操作
		HighlightOptions highlightOptions = new HighlightOptions();
		highlightOptions.addField("item_title");
		highlightOptions.setSimplePrefix("<font color = 'red'>");
		highlightOptions.setSimplePostfix("</font>");
		// 设置高亮查询
		query.setHighlightOptions(highlightOptions);
		HighlightPage<TbItem> highlightPage = solrTemplate.queryForHighlightPage(query, TbItem.class);
		// 获取查询总记录数
		long elements = highlightPage.getTotalElements();
		System.out.println("查询总记录数： "+elements);
		// 获取总记录数
		List<TbItem> list = highlightPage.getContent();
		// 遍历集合对象
		for (TbItem item : list) {
			// 获取高亮
			List<Highlight> highlights = highlightPage.getHighlights(item);
			Highlight highlight = highlights.get(0);
			List<String> snipplets = highlight.getSnipplets();
			System.out.println("高亮字段： "+snipplets);
			
		}
		
	}
	
	/**
	 * 删除全部数据
	 * 
	 */
	@Test
	public void deleteAll() {
		Query query = new SimpleQuery("*:*");
		solrTemplate.delete(query);
		solrTemplate.commit();
	}
}
