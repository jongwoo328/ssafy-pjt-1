<template>
  <!-- Two-way Data-Binding -->
  <quill-editor
    ref="myQuillEditor"
    v-model="content"
    :options="editorOption"
    @blur="onEditorBlur($event)"
    @focus="onEditorFocus($event)"
    @ready="onEditorReady($event)"
  />
</template>

<script>
import { quillEditor } from 'vue-quill-editor'

export default {
    data () {
        return {
        content: '',
        editorOption: {
            // Some Quill options...
        }
        }
    },
    components: {
        quillEditor
    },
    methods: {
        onEditorBlur() {
        // console.log('editor blur!', quill)
        },
        onEditorFocus() {
        // console.log('editor focus!', quill)
        },
        onEditorReady() {
        // console.log('editor ready!', quill)
        },
        onEditorChange({ html }) {
        // console.log('editor change!', quill, html, text)
        this.content = html
        }
    },
    computed: {
        editor() {
        return this.$refs.myQuillEditor.quill
        }
    },
    mounted() {
        console.log('this is current quill instance object', this.editor)
    }
}
</script>