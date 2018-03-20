package com.xiaozhao.bean;

import java.util.List;

public class NewsResult implements ListEntity<NewsResult.NewsBean> {


	/**
	 * msg : 1
	 * result : [{"click":"145","id":"2451","litpic":"http://pic.iqgtv.com/p_qgxw_2018012301.jpg","pubdate":"1516693200","title":"福厦高铁泉港段有最新进展啦！","writer":"庄凯丽, 庄伊洁（实习）"},{"click":"192","id":"2450","litpic":"http://pic.iqgtv.com/p_qgxw_20180122000.jpg","pubdate":"1516623780","title":"《福建日报》《福建新闻联播》竞相报道泉港，快来看看夸了啥！","writer":"泉港广电"},{"click":"155","id":"2449","litpic":"http://pic.iqgtv.com/p_qgxw_2018012201.jpg","pubdate":"1516609080","title":"锦川小区一期幼儿园有望3月完工","writer":"林伟平, 庄凯丽"},{"click":"144","id":"2448","litpic":"http://pic.iqgtv.com/p_qgxw_2018012102.jpg","pubdate":"1516525020","title":"院线电影《荒野召唤》摄制组来咱厝取景拍摄！","writer":"刘怡敏"},{"click":"57","id":"2447","litpic":"http://pic.iqgtv.com/p_qgxw_20180120001.jpg","pubdate":"1516456260","title":"好消息！锦川小区1731套安置房6月竣工！","writer":"林伟平, 庄凯丽"},{"click":"89","id":"2446","litpic":"http://pic.iqgtv.com/p_qgxw_2018012001.jpg","pubdate":"1516437900","title":"【大干40天 比拼开门红】后龙镇政府门口这条\u201c断头路\u201d快打通了！","writer":"庄凯丽, 唐钏淦"},{"click":"75","id":"2445","litpic":"http://pic.iqgtv.com/p_qgxw_20180119001.jpg","pubdate":"1516365540","title":"哇！很炫！政务审批进展大屏幕实时可视啦！","writer":"庄敏, 庄伊洁（实习）"},{"click":"139","id":"2444","litpic":"http://pic.iqgtv.com/p_qgxw_2018011901.jpg","pubdate":"1516354320","title":"【大干40天 比拼开门红】福州大学国字号研究中心建设稳步推进","writer":"肖伟川, 庄敏"},{"click":"126","id":"2441","litpic":"http://pic.iqgtv.com/p_qgxw_2018011701.jpg","pubdate":"1516195860","title":"幸福是奋斗出来的！区政府第一次全体会议定调2018！","writer":"泉港广电"},{"click":"171","id":"2440","litpic":"http://pic.iqgtv.com/p_qgxw_2018011501.jpg","pubdate":"1516004940","title":"此心安处是吾乡，回望2017泉港精神家园的12道亮光！","writer":"泉港广电"},{"click":"159","id":"2439","litpic":"http://pic.iqgtv.com/p_qgxw_2018011402.jpg","pubdate":"1515938760","title":"了不得！咱厝的文明新风昨晚上了央视《焦点访谈》！","writer":"泉港广电"},{"click":"129","id":"2438","litpic":"http://pic.iqgtv.com/p_qgxw_2018011301.jpg","pubdate":"1515855480","title":"泉港历史上最昂贵的宴席，历时一年，美不胜收！","writer":"唐钏淦, 庄凯丽, 肖远强（实习）"},{"click":"190","id":"2437","litpic":"http://pic.iqgtv.com/p_qgxw_2018011204.jpg","pubdate":"1515747120","title":"【大干40天 比拼开门红】实施\u201c5+2\u201d工程，提高坝头溪排洪量","writer":"唐钏淦, 庄敏"},{"click":"116","id":"2436","litpic":"http://pic.iqgtv.com/p_qgxw_20180111005.jpg","pubdate":"1515681300","title":"《公交司机见义勇为》后续报道：小海睿恢复良好，刘长海免受处罚，正能量持续发酵！","writer":"唐钏淦, 肖伟川"},{"click":"120","id":"2435","litpic":"http://pic.iqgtv.com/p_qgxw_2018011101.jpg","pubdate":"1515680580","title":"这是什么状况，海巡艇都派出来驱逐清障了！","writer":"刘怡敏, 郑敏（实习）"},{"click":"156","id":"2434","litpic":"http://pic.iqgtv.com/p_qgxw_20180110001.jpg","pubdate":"1515591720","title":"新年新作为：打造东进西出，南上北下海上交通枢纽！","writer":"庄敏, 唐钏淦"},{"click":"112","id":"2433","litpic":"http://pic.iqgtv.com/p_qgxw_2018010901.jpg","pubdate":"1515465600","title":"【大干40天 比拼开门红】老盐厂换新址 泉港海盐销八方","writer":"唐钏淦, 庄敏"},{"click":"64","id":"2413","litpic":"http://pic.iqgtv.com/p_qgxw_2018010802.jpg","pubdate":"1515414720","title":"昨晚，泉港秀溪移风易俗影像上央视《新闻联播》啦！","writer":"泉港广电"},{"click":"107","id":"2412","litpic":"http://pic.iqgtv.com/p_qgxw_2018010701.jpg","pubdate":"1515320880","title":"泉港公交司机见义勇为，连闯两次红灯，抢救受伤男孩！","writer":"泉港广电"},{"click":"50","id":"2411","litpic":"http://pic.iqgtv.com/p_qgxw_2018010601.jpg","pubdate":"1515248640","title":"每户补助3万，这种好事只有他们才有！","writer":"林伟平"}]
	 * type : imagetext
	 */

	private int msg;
	private String type;
	private List<NewsBean> result;

	public int getMsg() {
		return msg;
	}

	public void setMsg(int msg) {
		this.msg = msg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<NewsBean> getResult() {
		return result;
	}

	public void setResult(List<NewsBean> result) {
		this.result = result;
	}

	@Override
	public List<NewsBean> getList() {
		return result;
	}

	public static class NewsBean extends Entity {
		/**
		 * click : 145
		 * id : 2451
		 * litpic : http://pic.iqgtv.com/p_qgxw_2018012301.jpg
		 * pubdate : 1516693200
		 * title : 福厦高铁泉港段有最新进展啦！
		 * writer : 庄凯丽, 庄伊洁（实习）
		 */

		private String click;
		private String id;
		private String litpic;
		private String pubdate;
		private String title;
		private String writer;

		public String getClick() {
			return click;
		}

		public void setClick(String click) {
			this.click = click;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getLitpic() {
			return litpic;
		}

		public void setLitpic(String litpic) {
			this.litpic = litpic;
		}

		public String getPubdate() {
			return pubdate;
		}

		public void setPubdate(String pubdate) {
			this.pubdate = pubdate;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getWriter() {
			return writer;
		}

		public void setWriter(String writer) {
			this.writer = writer;
		}
	}
}
