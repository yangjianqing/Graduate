export default {
    components: {},
    props: [],
    data() {
      return {
        formData: {
          field101: undefined,
          field103: undefined,
          field105: undefined,
          field106: undefined,
        },
        rules: {
          field101: [{
            required: true,
            message: '请输入单行文本',
            trigger: 'blur'
          }],
          field106: [{
            required: true,
            message: '请输入单行文本',
            trigger: 'blur'
          }],
        },
      }
    },
    computed: {},
    watch: {},
    created() {},
    mounted() {},
    methods: {
      submitForm() {
        this.$refs['elForm'].validate(valid => {
          if (!valid) return
          // TODO 提交表单
        })
      },
      resetForm() {
        this.$refs['elForm'].resetFields()
      },
    }
  }