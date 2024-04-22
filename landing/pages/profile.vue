<script setup>
const user = userStore()

const state = reactive({
    name: user.name,
    email: user.email,
    phone: user.phone,
});

async function handleSubmit () {
    const userUpdated = user.getUser();

    userUpdated.name = state.name;
    userUpdated.email = state.email;
    userUpdated.phone = state.phone;
    user.name = state.name;
    user.email = state.email;
    user.phone = state.phone;
    const body = JSON.stringify(userUpdated);
    try {
        const response = await $fetch('http://localhost:8080/api/user', {
            method: 'PUT',
            body: body,
        });
        const tokenCookie = useCookie('token');
        const toast = useToast();
        toast.add({ title: 'Datos actualizados', color: 'primary' });
    } catch (error) {
        if (error.response.status === 400) {
            const toast = useToast();
            toast.add({ title: 'Error en los datos ingresados', color: 'red' });
        } else if (error.response.status === 409) {
            const toast = useToast();
            toast.add({ title: 'El correo ya está registrado', color: 'red' });
        } else {
            const toast = useToast();
            toast.add({ title: 'Error en el servidor', color: 'red' });
        }
    }
}

</script>

<template>
    <div class="flex justify-center items-center content-below-appbar">
        <UCard class="w-3/5 max-w-2xl">
            <h1 class="text-2xl font-bold text-primary text-center">Perfil</h1>
            <UForm :state="state" class="space-y-4 text-center" @submit="handleSubmit">
                <UFormGroup label="Nombre" name="name">
                    <UInput v-model="state.name" />
                </UFormGroup>
                
                <UFormGroup label="Email" name="email">
                    <UInput placeholder="you@example.com" v-model="state.email" />
                </UFormGroup>
                
                <UFormGroup label="Celular" name="phone" type="tel">
                    <UInput placeholder="+56912345678" v-model="state.phone" />
                </UFormGroup>
                
                <UButton class="w-1/3 max-w-52 justify-center" type="submit">
                    Editar datos
                </UButton>
            </UForm>
        </UCard>
    </div>
</template>

<style>
.content-below-appbar {
    height: calc(100vh - 72px)
}
</style>