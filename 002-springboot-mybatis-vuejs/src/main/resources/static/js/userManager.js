const app = new Vue({
    el: "#app",
    data() {
        return {
            msg: "helloworld",
            allUser: [],
            insertUserForm: {
                username: "",
                password: "",
                name: "",
                age: "",
                phone: "",
                email: "",
                status: 1,
            },
            insertUserRules: {},
        };
    },
    created() {
        this.selectAll();
    },
    methods: {

        selectAll() {
            axios({
                method: "GET",
                url: "http://localhost:18821/user/selectAll",
                responseType: "json"
            }).then(res => {
                // console.log(res);
                if (res.status == 200 && res.data.length != 0) {
                    this.allUser = res.data;
                } else {
                    alert("null");
                }
            });
        },
        resetForm() {
            this.insertUserForm = {
                username: "",
                password: "",
                name: "",
                age: "",
                phone: "",
                email: "",
                status: 1,
            };
        },
        submitForm() {
            // TODO: validate
            if (this.insertUserForm.username == "" ||
                this.insertUserForm.password == "" ||
                this.insertUserForm.name == "" ||
                this.insertUserForm.age == "" ||
                this.insertUserForm.age == "" ||
                this.insertUserForm.phone == "" ||
                this.insertUserForm.email == ""
            ) {
                this.$message({
                    showClose: true,
                    message: '请把表单填写全',
                    type: 'error'
                });
                return;
            }
            axios({
                method: "POST",
                url: "http://localhost:18821/user/insert",
                data: this.insertUserForm,
                responseType: "json"
            }).then(res => {
                console.log(res);
                if (res.status == 200 && res.data == true) {
                    this.$message({
                        showClose: true,
                        message: '新增成功',
                        type: 'success'
                    });
                    this.selectAll();
                    this.resetForm();
                } else {
                    this.$message({
                        showClose: true,
                        message: '插入失败',
                        type: 'error'
                    });
                }
            });
        },
        updateById(row) {
            this.$message({
                showClose: true,
                message: "UPDATE: " + JSON.stringify(row),
                type: 'primary'
            });
        },
        deleteById(row) {
            this.$confirm('此操作将永久删除该行, 是否继续?' + JSON.stringify(row), '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                axios({
                    method: "post",
                    url: "http://localhost:18821/user/delete",
                    data: new Number(row.id),
                }).then(res => {
                    // console.log(res);
                    if (res.status == 200 && res.data == true) {
                        this.$message({
                            showClose: true,
                            message: "删除成功",
                            type: 'success'
                        });
                        this.selectAll();
                    } else {
                        this.$message({
                            showClose: true,
                            message: "删除失败",
                            type: 'error'
                        });
                    }
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });


        },

    },
})