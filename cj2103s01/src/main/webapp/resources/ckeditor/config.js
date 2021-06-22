/**
 * @license Copyright (c) 2003-2021, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here.
	// For complete reference see:
	// https://ckeditor.com/docs/ckeditor4/latest/api/CKEDITOR_config.html

  // movie 아이콘보기
  config.extraPlugins = 'html5video';

  // 컬러스킨 변경...
config.skin = 'minimalist';

	// The toolbar groups arrangement, optimized for two toolbar rows.
	config.toolbarGroups = [
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'colors', groups: [ 'Textcolors' ] },
		{ name: 'document', groups: [ 'document', 'mode', 'doctools' ] },
		{ name: 'editing', groups: [ 'find', 'selection', 'editing' ] },
		{ name: 'insert', groups: [ 'insert' ] },
		{ name: 'clipboard', groups: [ 'undo', 'clipboard' ] },
		'/',
		{ name: 'forms', groups: [ 'forms' ] },
		{ name: 'links', groups: [ 'links' ] },
		{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
		{ name: 'styles', groups: [ 'styles' ] },
		{ name: 'tools', groups: [ 'tools' ] },
		{ name: 'others', groups: [ 'others' ] },
		{ name: 'about', groups: [ 'about' ] }
	];

	// Remove some buttons provided by the standard plugins, which are
	// not needed in the Standard(s) toolbar.
	config.removeButtons = 'BGColor,About,NewPage,ExportPdf,Print,Templates,PasteFromWord,Replace,Form,TextField,Textarea,Button,ImageButton,HiddenField,Select,Radio,Checkbox,CopyFormatting,RemoveFormat,Blockquote,CreateDiv,Anchor,Flash,Smiley,PageBreak,Iframe,Undo,Cut,Copy,Redo,Paste,PasteText';

	// Set the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';

	// Simplify the dialog windows.
	config.removeDialogTabs = 'image:advanced;link:advanced';
};
