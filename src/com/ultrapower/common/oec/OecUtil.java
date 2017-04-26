package com.ultrapower.common.oec;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dinfo.oec.daemon.client.DaemonClient;
import com.dinfo.oec.daemon.core.bean.CclassifyExpression;
import com.dinfo.oec.daemon.core.bean.ClassifyInfo;
import com.dinfo.oec.daemon.core.bean.Concept;
import com.dinfo.oec.daemon.core.bean.ConceptValue;
import com.dinfo.oec.daemon.core.bean.Element;
import com.dinfo.oec.daemon.core.bean.JobItem;
import com.dinfo.oec.daemon.core.bean.ParseInfo;
import com.dinfo.oec.daemon.core.bean.SclassifyInfo;
import com.ultrapower.common.util.PropertyUtil;
import com.ultrapower.webservice.bean.Topic;

/**
 * oec调用分析微博内容
 * 
 * @author yll
 * 
 */
public class OecUtil {
	/**
	 * C/S分类计算单条文本分析接口
	 * <p>
	 * 
	 * @param projectCode
	 *            项目编号
	 * @param dataminingType
	 *            挖掘类型，建模为beta，生产为release
	 * @param ontoId
	 *            本体编号
	 * @param rootClasId
	 *            根节点分类编号
	 * @param clasLevel
	 *            分类深度等级 -1为所有根结，根节点等级为0
	 * @param jobItem
	 *            文本信息类
	 * @return
	 */
	private static Log log = LogFactory.getLog(OecUtil.class);
	private static PropertyUtil instance = PropertyUtil.getInstance();
	private static String ip = instance.getProperty("oec.server.ip").trim();
	private static int port = Integer.parseInt(instance
			.getProperty("oec.server.port"));
	private static String projectCode = instance.getProperty("projectCode")
			.trim();
	private static String dataminingType = instance.getProperty(
			"dataminingType").trim();
	private static String ontoId = instance.getProperty("ontoId").trim();
	private static String rootClasId = instance.getProperty("rootClasId")
			.trim();
	private static String contoId = instance.getProperty("contoId").trim();
	private static String crootClasId = instance.getProperty("crootClasId")
			.trim();
	private static String qrootClasId = instance.getProperty("qrootClasId")
			.trim();
	private static String key_crootClasId = instance.getProperty(
			"key_crootClasId").trim();
	private static JobItem jobItem = new JobItem();
	//private static Set<String> set = new HashSet<String>();
	private static int clasLevel = -1;

	static DaemonClient client = null;

	public static DaemonClient getConnection() {
		if (client == null) {
			client = new DaemonClient(String.format("%s:%d", ip, port));
			return client;
		} else {
			return client;
		}
	}
	
	//关闭
	public static void close(){
			if(client != null){		
				client.closeConnection();
			}		
	}
		
	// 敏感关键字的匹配获取
	public  String keywords(String content) {
		StringBuffer s=new StringBuffer(" ");
		String m;
		String n="";
		List<ConceptValue> conceptValues = null; 		
		//去除字符串两端的空格
		jobItem.setContent(content.trim());
		
		// 数据挖掘调度客户端类
		// DaemonClient client = new DaemonClient(String.format("%s:%d", ip,port));
		client = getConnection();
		try {
			//boolean a = client.classifyLoadModelResource(projectCode,
			//		dataminingType, contoId);
			ParseInfo info = client.classifyParseSingleJobitem(projectCode,
					dataminingType, contoId, key_crootClasId, clasLevel,
					jobItem);
			List<ClassifyInfo> classifyInfos = info.getClassifyInfos();
			if (classifyInfos.size() != 0) {
				for (ClassifyInfo classifyInfo : classifyInfos) {
					List<CclassifyExpression> clasExpressions = classifyInfo.getClasExpressions();
						for (CclassifyExpression cExpression : clasExpressions) {
							List<Concept> concepts = cExpression.getConcepts();
							for (Concept concept : concepts) {
								conceptValues = concept.getConceptValues();
								
								for (ConceptValue conceptValue : conceptValues) {
									String matchContent = conceptValue.getMatchContent();
									StringBuffer t= new StringBuffer(matchContent);
									m=t.toString()+"/";
									if(n.contains(m)){
										m="";
									}
									n=m+n;
									
								}																
							}							
						}
					}
				}					
			if(n.length()!=0){
				n=n.substring(0, n.length()-1);
				s=(new StringBuffer(n)).append("|").append(false);			
			}else{
				s=(new StringBuffer(" ")).append("|").append(true);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return s.toString();
	}

	public JSONObject emotion(String content){
		/**
		 * 情感分析
		 */
		JSONObject json=new JSONObject();
		String nn=" ";
		ParseInfo info;
		jobItem.setContent(content.trim());
		client = getConnection();
		try {
			info = client.classifyParseSingleJobitem(projectCode,dataminingType, contoId,qrootClasId, clasLevel,jobItem);		
			List<ClassifyInfo> classifyInfos = info.getClassifyInfos();
			for (ClassifyInfo classifyInfo : classifyInfos) {
					List<CclassifyExpression> clasExpressions = classifyInfo.getClasExpressions();
					for (CclassifyExpression cExpression : clasExpressions) {
						List<Concept> concepts = cExpression.getConcepts();
						for (Concept concept : concepts) {
							List<ConceptValue> conceptValues = concept.getConceptValues();
							for (ConceptValue conceptValue : conceptValues) {
								String s=conceptValue.getConceptResource();
								String mm=s+"/";
								nn=mm+nn;
							}
							
							if(classifyInfo.getClasName().equals("正面")){
								json.put("正面",nn.substring(0, nn.length()-2));
							}else if(classifyInfo.getClasName().equals("负面")){
								json.put("负面", nn.substring(0, nn.length()-2));
							}else if(classifyInfo.getClasName().equals("中性")){
								json.put("中性", nn.substring(0, nn.length()-2));
							}													
						}
					}
					nn=" ";			
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}
	// 部门的推送
	public  JSONObject dept(String content) {
			JSONObject json=new JSONObject();
			Set<String> set=new HashSet<String>();
			String dept="";
			//String clasName = null;
			String elementName = null;
			JobItem jobItem = new JobItem();
			String con = content.trim();
			jobItem.setContent(con);
			// 数据挖掘调度客户端类
			client = getConnection();
			// 加载OEC模型资源
			//boolean a = client.sclassifyLoadModelResource(projectCode,
			//		dataminingType, ontoId);
			// 调用S分类接口分析文本
			/*ParseInfo info = client.sclassifyParseSingleJobitem(projectCode,
					dataminingType, ontoId, rootClasId, clasLevel, jobItem);
			List<SclassifyInfo> sclassifyInfos = info.getSclassifyInfos();
			for (SclassifyInfo sc : sclassifyInfos) {
				clasName = sc.getClasName();
				set.add(clasName);
			}*/
			try {
				// C分类文本分析
				ParseInfo info1 = client.classifyParseSingleJobitem(projectCode,
						dataminingType, contoId, crootClasId, clasLevel, jobItem);
				List<Element> matchElements = info1.getMatchElements();
				for (Element element : matchElements) {
					elementName = element.getElementName();
					set.add(elementName);
				}
				for(String str:set){					
					dept=str+"|"+dept;
				}
				json.put("deptname", dept.substring(0, dept.length()-1));
			} catch (Exception e) {
				log.info("文本内容不能为空", e);
			}
			return json;
		}
	
	public JSONObject keyUnitEmotion(String content){
		Topic t=new Topic();
		t.setCreatetime("");
		t.setTcontent(content);
		JSONObject json=new JSONObject();
		OecUtil oec = new OecUtil();
        String s=oec.keywords(content);		
        JSONObject obj=oec.emotion(content);
        String array[]=s.split("\\|");
        JSONObject djson=oec.dept(content);
        String deptname=djson.getString("deptname");
        json.put("tid", t.getTid());
		json.put("tcontent",t.getTcontent());
		json.put("creattime",t.getCreatetime() );
		json.put("viewcount",t.getViewcount() );
		json.put("replycount",t.getReplycount() );		
        json.put("sensitive", array[0]);
        json.put("status", array[1]);
        json.put("emotion", obj.toString());
        json.put("deptname", deptname);
		return json;
		
	}

	public static void main(String[] args) {
		
		OecUtil oec = new OecUtil();
		String scontent = "教务处信息办保卫处食堂可以办卡吗同意卧槽 居然可以打那么多字 感动 最右被你们顶没了你们造吗！（哇居然不是限140字耶好评！ 看到大家都在努力建设中国特色社会主义我就放心了[呵呵] 富强 民主 文明 和谐 自由 平等 公正 法治 爱国 敬业 诚信 友善 我真的不过只是想和裴校长一起建设社会主义新校园而已啊。 真的不是建设社会主义接班人？ 最右只是想找个女朋友跟他一起建设社会主义 👉👉👉帮右边火🔥 帮右边→→ 帮右边→_→ 帮右边火→→ 👉👉 👉👉 帮右边火👉👉";
		JSONObject json=oec.keyUnitEmotion(scontent);
		System.out.println(json.toString());
		
	}	
}
	

