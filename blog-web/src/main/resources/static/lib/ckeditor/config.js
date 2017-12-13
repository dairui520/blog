/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	config.language = 'zh-cn';
	config.toolbar = 'blog';
	config.skin = 'minimalist';
	config.height = 450;
	
	config.filebrowserUploadUrl="/ckeditor/upload.do";
	
	config.toolbar_blog = [
   	   [ 'Cut', 'Copy', 'Paste', '-',
   	     'Undo', 'Redo', '-',
   	     'Link', 'Unlink', '-',
   	     'TextColor', 'BGColor', '-',
   	     'Image','Flash', 'Table', 'HorizontalRule' , '-',
   	     'Source', 'Maximize' ],
   	   '/',
   	   [ 'Bold', 'Italic', 'Strike', '-',
   	     'NumberedList', 'BulletedList', 'Outdent', 'Indent', '-',
   	     'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-',
   	     'Blockquote', 'CodeSnippet', '-',
   	     'Format', 'Font', 'FontSize' ]
	];

	config.format_tags = 'p;h1;h2;h3;h4;h5;h6';
	config.codeSnippet_theme = 'solarized_light';
	config.font_names = 
			'默认/Lantinghei SC, Open Sans, Arial, Hiragino Sans GB, Microsoft YaHei, STHeiti, WenQuanYi Micro Hei, SimSun, sans-serif;' +
			'宋体/宋体, SimSun;' +
			'微软雅黑/微软雅黑, Microsoft YaHei;' +
			'楷体/楷体, 楷体_BG2312, SimKai;' +
			'黑体/黑体, SimHei;' +
			'隶书/隶书, SimLi;' +
            'Consolas;'+
			'Arial/Arial, Helvetica, sans-serif;' +
			'Comic Sans MS/Comic Sans MS, cursive;' +
			'Courier New/Courier New, Courier, monospace;' +
			'Georgia/Georgia, serif;' +
			'Lucida Sans Unicode/Lucida Sans Unicode, Lucida Grande, sans-serif;' +
			'Tahoma/Tahoma, Geneva, sans-serif;' +
			'Times New Roman/Times New Roman, Times, serif;' +
			'Trebuchet MS/Trebuchet MS, Helvetica, sans-serif;' +
			'Verdana/Verdana, Geneva, sans-serif';
	config.codeSnippet_languages = {
	    apache: 'Apache',
	    bash: 'Bash',
	    cs:'C#',
	    cpp:'C++',
	    css:'CSS',
	    coffeescript:'CoffeeScript',
	    delphi:'Delphi',
	    diff:'Diff',
	    go:'Go',
	    groovy:'Groovy',
	    html:'HTML',
	    http:'HTTP',
	    ini:'Ini',
	    json:'JSON',
	    java:'Java',
	    javascript:'JavaScript',
	    lua:'lua',
	    makefile:'Makefile',
	    markdown:'Markdown',
	    nginx:'Nginx',
	    objectivec:'Objective-C',
	    php:'PHP',
	    perl:'Perl',
	    python:'Python',
	    ruby:'Ruby',
	    scala:'Scala',
	    scss:'SCSS',
	    sql:'SQL',
	    swift:'Swift',
	    vbscript:'VBScript',
	    xml:'XML',
	};
	config.removeDialogTabs = 'link:advanced;link:upload;image:advanced;image:Link;flash:Upload';
	config.removeButtons = 'Subscript,Superscript,Anchor';
	config.tabSpaces = 4;
    config.font_defaultLabel = '默认';
    config.allowedContent = true;
    config.format_p = { element: 'p', attributes: { 'class': 'normalPara' } };
};
