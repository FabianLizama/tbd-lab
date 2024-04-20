<script setup>
const user = useState('user');

const state = reactive({
    userType: 0,
    name: user.value.name,
    email: user.value.email,
    phone: user.value.phone,
});

async function handleSubmit () {
    const result = schema.safeParse(state);
    if (!result.success) {
        const toast = useToast();
        toast.add({ title: 'Error en los datos ingresados', color: 'red' });
        return;
    }

    const body = JSON.stringify({
        name: state.name,
        email: state.email,
        phone: state.phone
    });
    try {
        const response = await $fetch('http://localhost:8080/api/user/update', {
            method: 'PUT',
            body: body,
        });
        const tokenCookie = useCookie('token');
        tokenCookie.value = response;
        const router = useRouter();
        router.push('/');
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