<script setup>
// lógica tabla de emergencias
const tokenCookie = useCookie('token');

const em_columns = [{
    key: 'emergency_id',
    label: 'ID',
}, {
    key: 'name',
    label: 'Emergencia',
}
]
/*
const emergencies = await $fetch('http://localhost:8080/api/emergency/view', {
  method: 'GET',
  params: {
    token: tokenCookie.value
  }
})
*/
const emergencies = ref([])



const em_page = ref(1)
const em_pageCount = 10

const em_rows = computed(() => {
  return emergencies.value.slice((em_page.value - 1) * em_pageCount, (em_page.value) * em_pageCount)
})

const selectedRegion = ref('')

const regions = [
  "Región Metropolitana",
  "Región de Valparaíso",
  "Región de Coquimbo",
  "Región de Atacama",
  "Región de Antofagasta",
  "Región de Tarapacá",
  "Región de Arica y Parinacota",
  "Región del Libertador General Bernardo O Higgins",
  "Región del Maule",
  "Región de Ñuble",
  "Región del Biobío",
  "Región de la Araucanía",
  "Región de Los Ríos",
  "Región de Los Lagos",
  "Región Aysén del General Carlos Ibañez del Campo",
  "Región de Magallanes y de la Antartica Chilena"
];

async function fetchEmergencies() {
  try {
    emergencies.value = await $fetch('http://localhost:8080/api/emergency/region', {
      method: 'GET',
      params: {
        token: tokenCookie.value,
        name: selectedRegion.value
      }
    })
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
  <div class="flex items-center justify-center content-below-appbar">
    <UCard>
        <h1 class="text-primary text-center text-inherit text-2xl pb-5 pt-2">Emergencias</h1>
        <UFormGroup label="Región" name="region">
            <USelect v-model="selectedRegion" :options="regions" @change="fetchEmergencies" />
        </UFormGroup>
        <UTable :rows="em_rows" :columns="em_columns"/>
        <div class="flex justify-end px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
          <UPagination v-if="emergencies.value" v-model="em_page" :page-count="em_pageCount" :total="emergencies.value.length" />
        </div>
    </UCard>
  </div>
</template>

<style>
.content-below-appbar {
    height: calc(100vh - 72px)
}
</style>