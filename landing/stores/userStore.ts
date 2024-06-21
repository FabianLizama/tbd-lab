export const userStore = defineStore('user', {
    state: () => ({
        user_id: -1,
        type_user_id: -1,
        name: '',
        email: '',
        phone: ''
    }),
    actions: {
        setUser(user: any) {
            this.user_id = user.user_id
            this.type_user_id = user.type_user_id
            this.name = user.name
            this.email = user.email
            this.phone = user.phone
        },
        getUser() {
            return {
                user_id: this.user_id,
                type_user_id: this.type_user_id,
                name: this.name,
                email: this.email,
                phone: this.phone
            }
        }
    },
    persist: true
})