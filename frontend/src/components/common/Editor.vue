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