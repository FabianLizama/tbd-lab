<script setup>
// Lógica del selector de ubicación
import { ref, onMounted } from 'vue'
import { Loader } from '@googlemaps/js-api-loader'

const initialCenter = { lat: -33.4489, lng: -70.6693 } // Coordenadas de Santiago, Chile
const center = ref({ ...initialCenter })
const selectedLocation = ref({ ...initialCenter })
const inputLocalization = ref('Santiago de Chile, Región Metropolitana, Chile')
const config = useRuntimeConfig()
let map
let marker

const initAutocomplete = () => {
    const input = document.getElementById('input-location')
    const autocomplete = new google.maps.places.Autocomplete(input, {
        componentRestrictions: { country: 'cl' } // Restringir sugerencias a Chile
    })
    autocomplete.addListener('place_changed', () => {
        const place = autocomplete.getPlace()
        if (place.geometry) {
            selectedLocation.value = {
                lat: place.geometry.location.lat(),
                lng: place.geometry.location.lng(),
            }
            center.value = selectedLocation.value
            inputLocalization.value = place.formatted_address
            map.setCenter(selectedLocation.value)
            marker.setPosition(selectedLocation.value)
        }
    })
}

const resetMap = () => {
    center.value = { ...initialCenter }
    selectedLocation.value = { ...initialCenter }
    inputLocalization.value = 'Santiago de Chile, Región Metropolitana, Chile'
    map.setCenter(center.value)
    marker.setPosition(center.value)
}

onMounted(() => {
    const loader = new Loader({
        apiKey: 'AIzaSyDxMgO4Z6RYYugJnQvrc6Iyjt9Ea6coMbo',
        version: 'weekly',
        libraries: ['places'],
    })

    loader.load().then(() => {
        map = new google.maps.Map(document.getElementById('map'), {
            center: center.value,
            zoom: 15,
        })

        marker = new google.maps.Marker({
            position: center.value,
            map: map,
        })

        map.addListener('click', (event) => {
            selectedLocation.value = {
                lat: event.latLng.lat(),
                lng: event.latLng.lng(),
            }
            inputLocalization.value = `Lat: ${selectedLocation.value.lat}, Lng: ${selectedLocation.value.lng}`
            marker.setPosition(selectedLocation.value)
        })

        initAutocomplete()
    })
})

//
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
            latitude: selectedLocation.value.lat,
            longitude: selectedLocation.value.lng
        }
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
                <UFormGroup label="Ubicación" name="ubication">
                    <UInput
                        size="xl" 
                        placeholder="Dirección" 
                        v-model="inputLocalization" 
                        icon="i-heroicons-map-pin" 
                        id="input-location"
                    />
                    <UContainer class="flex py-8 justify-center">
                        <div id="map" class="w-full" style="height: 300px"></div>
                    </UContainer>
                    <div class="flex justify-center py-4">
                        <UButton @click="resetMap">Resetear Mapa</UButton>
                    </div>
                </UFormGroup>
                <UButton class="w-1/3 max-w-52 justify-center" type="submit">
                    Confirmar
                </UButton>
            </UForm>
        </UCard>
    </div>
</template>