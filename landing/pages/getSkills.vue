<script setup>
import { z } from 'zod';
import { reactive } from 'vue';
import { useRouter } from 'vue-router';

const schema = z.object({
    rut: z.string().regex(/^\d{7,8}-\d{1}$/, 'RUT inválido')
});

const state = reactive({
    rut: '',
    skills: [],
    errorMessage: ''
});

const router = useRouter();

async function handleSubmit(event) {
    event.preventDefault();
    state.errorMessage = '';
    const result = schema.safeParse(state);
    if (!result.success) {
        state.errorMessage = 'RUT inválido o No contiene habilidades';
        return;
    }
    
    try {
        const response = await $fetch(`http://localhost:8080/volunteers/skills/${state.rut}`, {
            method: 'GET'
        });
        state.skills = response;
        if (state.skills.length === 0) {
            state.errorMessage = 'No se encontró el usuario, o no contiene Skills.';
        }
    } catch (error) {
        state.skills = [];
        state.errorMessage = 'No se encontró el usuario, o no contiene Skills.';
    }
}
</script>

<template>
    <div class="flex justify-center items-center content-below-appbar">
        <div class="card">
            <h1 class="title">Buscar Habilidades</h1>
            <form @submit="handleSubmit" class="form">
                <div class="form-group">
                    <label for="rut" class="form-label">RUT</label>
                    <input id="rut" name="rut" v-model="state.rut" placeholder="20000002-3" class="form-input"/>
                </div>
                <button type="submit" class="btn">Buscar</button>
            </form>
            <div v-if="state.errorMessage || state.skills.length > 0" class="skills">
                <h2 class="skills-title">Habilidades</h2>
                <div v-if="state.errorMessage" class="error-message">
                    {{ state.errorMessage }}
                </div>
                <ul v-else>
                    <li v-for="skill in state.skills" :key="skill.code" class="skill-item">
                        <h3 class="skill-name">{{ skill.name }}</h3>
                        <p><strong>Código:</strong> {{ skill.code }}</p>
                        <p><strong>Items:</strong> {{ skill.items.join(', ') }}</p>
                        <p><strong>Descripción:</strong> {{ skill.description }}</p>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<style scoped>
.content-below-appbar {
    height: calc(100vh - 72px);
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #1a1a1a;
}

.card {
    background: #fff;
    padding: 2rem;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    max-width: 500px;
    width: 100%;
}

.title {
    font-size: 2rem;
    color: #22c55e;
    text-align: center;
    margin-bottom: 1rem;
}

.form {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}

.form-group {
    display: flex;
    flex-direction: column;
}

.form-label {
    font-size: 1rem;
    color: #6b7280;
    margin-bottom: 0.5rem;
}

.form-input {
    padding: 0.5rem;
    border: 1px solid #d1d5db;
    border-radius: 4px;
    font-size: 1rem;
    color: #374151;
    background-color: #f9fafb;
    outline: none;
    transition: border-color 0.3s ease;
}

.form-input:focus {
    border-color: #2563eb;
}

.btn {
    background-color: #2563eb;
    color: #fff;
    padding: 0.75rem;
    border: none;
    border-radius: 4px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.btn:hover {
    background-color: #1d4ed8;
}

.skills {
    margin-top: 2rem;
}

.skills-title {
    font-size: 1.5rem;
    color: #22c55e;
    text-align: center;
    margin-bottom: 1rem;
}

.skill-item {
    background: #111827; /* Dark background for better visibility */
    padding: 1rem;
    border: 1px solid #e5e7eb;
    border-radius: 4px;
    margin-bottom: 1rem;
    color: #f9fafb; /* Light text color for dark background */
}

.skill-name {
    font-size: 1.25rem;
    color: #f9fafb; /* Light text color for dark background */
    margin-bottom: 0.5rem;
}

.error-message {
    color: red;
    text-align: center;
    margin-top: 1rem;
    background: #fff5f5;
    padding: 1rem;
    border: 1px solid #f5c2c2;
    border-radius: 4px;
}
</style>
