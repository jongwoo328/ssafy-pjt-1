<template>
  <!-- Two-way Data-Binding -->
  <quill-editor
    ref="myQuillEditor"
    v-model="content"
    :options="editorOption"
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
        onEditorChange({ html }) {
        this.content = html
        }
    },
    computed: {
        editor() {
        return this.$refs.myQuillEditor.quill
        }
    },
}
</script>