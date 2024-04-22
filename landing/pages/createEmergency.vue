<script setup>
const tokenCookie = useCookie('token');
const user = userStore();
const state = reactive({
    name: '',
    institution: '',
    estado: '',
});
const institutions = await $fetch('http://localhost:8080/api/institution');

const coordinator_id = await getCoorditatorId(user.user_id);

async function getCoorditatorId(id) {
    try {
        const response = await $fetch(`http://localhost:8080/api/coordinator/user/${id}`);
        console.log(response);
        return response;
    } catch (error) {
        const toast = useToast();
            toast.add({ title: 'Error en el servidor', color: 'red' });
    }
}

async function handleSubmit () {
    try {
        const createdEmergency = {
            coordinator_id: coordinator_id,
            name: state.name,
            emergency_state: 'Created',
            institution_id: state.institution,
        }
        console.log(createdEmergency)
        const body = JSON.stringify(createdEmergency);
        const response = await $fetch('http://localhost:8080/api/emergency/create', {
            method: 'POST',
            body: body,
            params: {
                token: tokenCookie.value
            }
        });
        const toast = useToast();
        toast.add({ title: 'Emergencia creada', color: 'primary' });
    } catch (error) {
        if (error.response.status === 400) {
            const toast = useToast();
            toast.add({ title: 'Error en los datos ingresados', color: 'red' });
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
            <h1 class="text-2xl font-bold text-primary text-center">Crear Emergencia</h1>
            <UForm :state="state" class="space-y-4 text-center" @submit="handleSubmit">
                <UFormGroup label="Nombre Emergencia" name="emergencyname">
                    <UInput v-model="state.name" />
                </UFormGroup>

                <UFormGroup label="Institución" name="institution">
                    <USelect v-model="state.institution" :options="institutions" option-attribute="institution_name" value-attribute="institution_id"/>
                </UFormGroup>
                
                <UButton class="w-1/3 max-w-52 justify-center" type="submit">
                    Confirmar
                </UButton>
            </UForm>
        </UCard>
    </div>
</template>