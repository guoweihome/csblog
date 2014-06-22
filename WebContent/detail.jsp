<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html dir="ltr" lang="zh-CN" class="no-js">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>标题</title>
<meta name="keywords" content="keywords">
<meta name="description" content="description">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--[if lte IE 6]>
<script>alert("本网站不支持IE6，请升级的你浏览器版本，或使用Chrome，Opera， Firefox以获得更好的效果。")</script>
<![endif]-->
<link href="http://libs.baidu.com/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">
<link href="http://libs.baidu.com/bootstrap/2.3.2/css/bootstrap-responsive.min.css"	rel="stylesheet">
<link rel="stylesheet" id="bs-c-css" href="./res/css/style.css"	type="text/css" media="all">

<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/2.3.2/js/bootstrap.min.js"></script>
<!--[if lt IE 9]>
<script src="./res/js/css3-mediaqueries.js"></script>	
<script src="./res/js/html5.js"></script>
<![endif]-->
</head>


<body>

<header>
<div id="inner-header" class="clearfix">
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid nav-container">
				<nav>
					<a class="brand" id="logo" title=""
						href="./index.jsp">CSDIG – 软件挖据</a> <a class="btn btn-navbar"
						data-toggle="collapse" data-target=".nav-collapse"> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
					</a>

					<div class="nav-collapse">
						<ul id="menu-bootstrap-2" class="nav">
							<li id="menu-item-848"
								class="menu-item menu-item-type-custom menu-item-object-custom">
								<a href="http://im.crackedzone.com/">首页</a>
							</li>
							<li id="menu-item-854"
								class="dropdown menu-item menu-item-type-custom menu-item-object-custom"><a
								href="http://im.crackedzone.com/" class="dropdown-toggle"
								data-toggle="dropdown">博客<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li id="menu-item-857"
										class="menu-item menu-item-type-taxonomy menu-item-object-category"><a
										href="http://www.crackedzone.com/category/crphp">PHP</a></li>
									<li id="menu-item-858"
										class="menu-item menu-item-type-taxonomy menu-item-object-category"><a
										href="http://www.crackedzone.com/category/crphp/codeigniter">CodeIgniter</a></li>
								</ul>
							</li>
							<li id="menu-item-855"
								class="menu-item menu-item-type-custom menu-item-object-custom"><a
								href="http://im.crackedzone.com/tools">工具</a></li>
							<li id="menu-item-850"
								class="menu-item menu-item-type-custom menu-item-object-custom"><a
								href="http://demo.crackedzone.com/">示例</a></li>
							<li id="menu-item-856"
								class="menu-item menu-item-type-custom menu-item-object-custom"><a
								href="http://im.crackedzone.com/resume">简历</a></li>
						</ul>
					</div>

				</nav>

				<form class="navbar-search pull-right form-search" method="get" id="searchform"	action="#">
					<div class="input-append">
						<input name="s" id="s" type="text"
							class="span2 search-query placeholder" autocomplete="off"
							placeholder="搜索" data-provide="typeahead" data-items="4"
							data-source="[&quot;PHP命令行下模拟Session机制&quot;,&quot;服务端需要一个新的分层 -“数据缓存层”&quot;,&quot;PHPUnit单元测试YAF控制层&quot;,&quot;PHPUnit单元测试YAF模型层&quot;,&quot;文章/视频Tags数据库结构设计&quot;,&quot;使用nginx-http-concat自动合并静态资源，加速你的网站请求速度&quot;,&quot;Javascript获取远程图片宽高&quot;,&quot;让sql语句不排序,按照in语句的顺序返回结果&quot;,&quot;安装Phabricator代码审核工具&quot;,&quot;sphinx/coreseek安装&quot;,&quot;Linux Git安装&quot;,&quot;phpDocumentor安装与使用&quot;,&quot;MongoDB学习笔记三 – 用户授权&quot;,&quot;MongoDB 学习笔记二&quot;,&quot;MongoDB 学习笔记一&quot;,&quot;Lnmp安装MongoDB以及PHP扩展&quot;,&quot;Nodejs – 初学安装&quot;,&quot;HTTPSQS简单队列服务的应用安装&quot;,&quot;创建一个类似Youtube的Id – 使用PHP/Python/Javascript/Java/SQL&quot;,&quot;MySql更新多条数据不建议使用UPDATE语句&quot;,&quot;Windows上使用CSS合并打包工具css-combo&quot;,&quot;九个PHP很有用的功能&quot;,&quot;float:left li中文标签在IE7中被自动折行&quot;,&quot;多次重定向中http-referer的变化&quot;,&quot;在Linux下实现Mysql远程访问&quot;,&quot;Vmware上通过NAT方式与Linux通信，FTP访问小结&quot;,&quot;Bootstrap process bar 制作 iframe loading效果&quot;,&quot;301重定向html页面 – 利用404页&quot;,&quot;快速搭建你的前端工程 – Bootstrap&quot;,&quot;Chrome视频音频下载插件 – FVD Video Downloader&quot;,&quot;GoAgent图文设置教程：从此翻墙无压力&quot;,&quot;Sublime Text 2开发工具用法心得&quot;,&quot;整理网站&quot;,&quot;网站备案成功&quot;,&quot;[转载]图解Google搜索秘籍：使用快捷键 善用数据库&quot;,&quot;jQuery实现鼠标点击Div区域外隐藏Div&quot;,&quot;jquery美化下拉框select插件czCombobox ( 原创 )&quot;,&quot;PHP中去除特殊空白换行方法&quot;,&quot;文章系统中的Content html保存&quot;,&quot;Html email标签Mailto标签的使用方法&quot;]">
						<button type="submit" class="btn">
							<i class="icon-search"></i>
						</button>
					</div>
				</form>
				
			</div>
		</div>
	</div>
</div>
<!-- end #inner-header -->
</header>
<!-- end header -->
<div class="container-fluid">

	<div id="content" class="clearfix row-fluid">
		
		
		<div id="main" class="span9 clearfix">
			
				<article class="post type-post status-publish format-standard hentry category-linux tag-coreseek tag-sphinx tag-install shadow-box clearfix">
					<header>
						<div class="page-header">
							<h1 class="single-title">
								<a href="http://www.crackedzone.com/sphinx-coreseek-instal.html"
									rel="bookmark" title="sphinx/coreseek安装">sphinx/coreseek安装</a>
							</h1>

							<p class="meta">
								<i class="icon-calendar"></i>
								<time datetime="2013-08-17">2013-08-17</time>
								<i class="icon-eye-open" title="点击率"></i> 23 <i
									class="icon-comment" title="评论"></i> <a
									href="http://www.crackedzone.com/sphinx-coreseek-instal.html#respond"
									title="《sphinx/coreseek安装》上的评论">0</a> <i class="icon-flag"></i>
								<a href="http://www.crackedzone.com/category/linux"
									title="查看 Linux 中的全部文章" rel="category tag">Linux</a>.
							</p>
						</div>

					</header>
					<!-- end article header -->

					<section class="post_content clearfix">
						<p>
							Sphinx 官方版本安装<br> wget
							http://sphinxsearch.com/files/sphinx-2.0.3-release.tar.gz<br>
							tar zxvf sphinx-2.0.3-release.tar.gz<br> ./configure
							–prefix=/usr/local/sphinx –with-mysql<br> make &amp;&amp;
							make install<br> cd /usr/local/sphinx<br> cp
							etc/sphinx-min.conf.dist etc/sphinx.conf<br>
							测试数据库，新建test数据库，导入etc/example.sql做测试<br> #修改mysql帐户密码<br>
							vim etc/sphinx.conf<br> #建立索引<br> bin/indexer -c
							etc/sphinx.conf test1<br> #查询<br> bin/search -c
							etc/sphinx.conf test<br> #开启守护进程<br> bin/searchd -c
							etc/sphinx.conf
						</p>
						<h4>五．小结</h4>
<p>通过策略模式模拟一个虚拟的Session功能，保证Session在命令行下能够正常工作，为项目的自动化测试提供了基本支持。</p>
<p>策略模式其用意在于封装了一组新的算法，基于不同的策略下能够互相替换，为此我们能够在自动化测试中模拟出更多的功能，如请求的Request功能，渲染的View功能等。</p>
<hr />
原创文章，转载请注明：</p>
<p>原文标题：PHP命令行下模拟Session机制</p>
<p>原文地址：<a href="http://www.crackedzone.com/php-cli-using-session-strategy.html" target="_blank">http://www.crackedzone.com/php-cli-using-session-strategy.html</a></p>
						
					</section>
					<!-- end article section -->

					<footer>

						<p class="tags pull-left">
							<span class="tags-title">Tags:</span> <a
								href="http://www.crackedzone.com/tag/coreseek" rel="tag"
								class="label">coreseek</a> <a
								href="http://www.crackedzone.com/tag/sphinx" rel="tag"
								class="label">sphinx</a> <a
								href="http://www.crackedzone.com/tag/install" rel="tag"
								class="label">安装</a>
						</p>

					<h4>相关文章</h4>
						<ul id="bones-related-posts">
							<li class="related_post">
							<a href="http://www.crackedzone.com/cookie-session-discuss.html" title="PHP cookie和session的分析">PHP cookie和session的分析</a>
							</li>
	        			</ul>
					</footer>
					<!-- end article footer -->

				</article>
				<!-- end article -->
			



<section id="respond" class="respond-form shadow-box">
	<h3 id="comment-form-title">评论</h3>

	<div id="cancel-comment-reply">
		<p class="small"><a rel="nofollow" id="cancel-comment-reply-link" href="/php-cli-using-session-strategy.html#respond" style="display:none;">取消</a></p>
	</div>

	
	<form action="http://www.crackedzone.com/wp-comments-post.php" method="post" class="form-horizontal" id="commentform">

		
	<div class="control-group">
		<label for="author" class="control-label">Name</label>
		<div class="controls">
			<div class="input-prepend">
			  	<span class="add-on"><i class="icon-user"></i></span><input type="text" name="author" id="author" value="" placeholder="Your Name" tabindex="1" aria-required='true' />
			</div>
			<span class="help-inline"><font color='red'>*</font></span>
		</div>
	</div>

	<div class="control-group">
	  	<label for="email" class="control-label">E-Mail</label>
	  	<div class="controls">
		  	<div class="input-prepend">
		  		<span class="add-on"><i class="icon-envelope"></i></span><input type="email" name="email" id="email" value="" placeholder="example@126.com" tabindex="2" aria-required='true' />
		  	</div>
		  	<span class="help-inline"><font color='red'>*</font></span>
		</div>

  	</div>

	<div class="control-group">
	  	<label for="url" class="control-label">Site</label>
	  	<div class="controls">
	  		<div class="input-prepend">
		  	<span class="add-on"><i class="icon-home"></i></span><input type="text" name="url" id="url" value="" placeholder="http://www.example.com" tabindex="3" />
		  	</div>
		</div>
  	</div>

		
	<div class="clearfix">
		<label for="comment" class="control-label">Comment</label>
		<div class="controls">
			<textarea name="comment" id="comment" rows="5" placeholder="Your Comment Here…" tabindex="4"></textarea>
		</div>
	</div>
	
	<div class="form-actions">
		<input type="hidden" name="comment_post_hash" value="" id="comment_post_hash">
	  	<input class="btn btn-primary" name="submit" type="submit" id="submit" tabindex="5" value="提交" />
	  	<input type='hidden' name='comment_post_ID' value='974' id='comment_post_ID' />
<input type='hidden' name='comment_parent' id='comment_parent' value='0' />
	</div>
	
		</form>
	
		<script type="text/javascript">
	$("#submit").bind('click', function() {
		$("#comment_post_hash").val($("#comment_post_ID").val());
	});
	</script>
</section>





			
		</div>
		<!-- end #main -->
	
		<div id="sidebar1" class="fluid-sidebar sidebar span3">
				<div class="widget widget_tag_cloud">
					<h4 class="widgettitle">标签</h4>
					<div class="tagcloud">
						<div id="tag-cloud">
							<a href="./tag.jsp"	class="label label-success" title="3 个话题" style="font-size: 10px;">Apache</a> 
							<a href="./tag.jsp"	class="label" title="3 个话题" style="font-size: 10px;">Apache2</a> 
							<a href="./tag.jsp"	class="label label-info" title="3 个话题" style="font-size: 10px;">Apache3</a> 
							<a href="./tag.jsp"	class="label label-success" title="3 个话题" style="font-size: 10px;">Apache4</a> 
							<a href="./tag.jsp"	class="label label-important" title="3 个话题" style="font-size: 10px;">php</a> 
							<a href="./tag.jsp"	class="label label-success" title="3 个话题" style="font-size: 10px;">Apache6</a> 
							<a href="./tag.jsp"	class="label label-warning" title="3 个话题" style="font-size: 10px;">jquery</a> 
						</div>
					</div>
				</div>
				<div id="recent-comments-3" class="widget widget_recent_comments">
					<h4 class="widgettitle">近期评论</h4>
					<ul id="recentcomments">
						<li class="recentcomments">
						zhoushen 发表在《<a href="http://www.crackedzone.com/jquery_plugin_adding_callback_function.html#comment-1894">jQuery插件添加回调方法</a>》
						</li>
					</ul>
				</div>
				<div id="linkcat-2" class="widget widget_links">
					<h4 class="widgettitle">友情链接</h4>
					<ul class="xoxo blogroll">
						<li><a href="http://www.sunzhenghua.com/" target="_blank">2013的某一天</a></li>
						<li><a href="http://www.mysql100.com/" target="_blank">mysql100</a></li>
					</ul>
				</div>
			</div>
	
	
	</div>
	<!-- end #content -->
	<footer>
		<div id="inner-footer" class="clearfix">
			<hr>
			<p class="pull-left">© csdig</p>
			<p class="pull-right">
				Powered by <a href="http://www.csdig.com/" target="_blank">csdig</a>.
				Design by <a href="http://www.csdig.com/" title="">csdig</a>
			</p>
			<div class="clearfix"></div>
		</div>
	</footer>
</div>



</body>
</html>
