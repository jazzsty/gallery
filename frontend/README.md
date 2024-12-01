# frontend

vinit 설정

package.json 
"rules": {
      "vue/multi-word-component-names" : 0
    }

vue.json
{
	"Generate Basic Vue Code": {
		"prefix": "vinit",
		"body": [
			"<template>\n\t<div>\n\t\t\n\t</div>\n</template>\n<script>\n\texport default {\n\t\t\n\t}\n</script>\n<style>\n</style>"
		],
		"description": "Generate Basic Vue Code"
	}
}

정렬이 않되는것은
<template lang=""> -> <template> 변경 하면 해결된다
