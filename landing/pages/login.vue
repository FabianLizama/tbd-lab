<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const state = ref({
  email: '',
  password: '',
});

async function handleSubmit() {
  try {
    await $fetch(`http://localhost:8080/login/${state.value.email}/${state.value.password}`, {
      method: 'GET',
    });
    
    // Si el login es exitoso, redirigimos a getSkills
    router.push('/getSkills');
  } catch (error) {
    const toast = useToast();
    toast.add({ title: 'Error de inicio de sesión', color: 'red' });
  }
}
</script>

<template>
  <div class="flex justify-center items-center content-below-appbar">
    <UCard class="w-3/5 max-w-2xl">
      <h1 class="text-primary font-bold text-center text-inherit text-3xl py-5">Inicio de Sesión</h1>
      <UForm :state="state" class="space-y-4 text-center" @submit="handleSubmit">
        <UFormGroup label="Email" name="email">
          <UInput placeholder="you@example.com" v-model="state.email" />
        </UFormGroup>
        
        <UFormGroup label="Contraseña" name="password">
          <UInput v-model="state.password" type="password" />
        </UFormGroup>
        <UButton class="w-1/3 max-w-52 justify-center" type="submit">
          Submit
        </UButton>
        <UFormGroup>
          <ULink
            to="/register"
            class="text-primary text-end underline text-sm"
          >
            No tengo una cuenta
          </ULink>
        </UFormGroup>
      </UForm>
    </UCard>
  </div>
</template>

<style>
.content-below-appbar {
  height: calc(100vh - 72px)
}
</style>