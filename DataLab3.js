// Conectar a MongoDB
conn = new Mongo();
db = conn.getDB("lab3");

// Insertar habilidades en la colección 'Skill'
const skills = [
    { code: "FIRE01", name: "Extinción de incendios", items: ["Manguera", "extintor"], description: "Habilidades para combatir incendios forestales" },
    { code: "RESC02", name: "Rescate de personas", items: ["Cuerdas", "arnés"], description: "Habilidades para el rescate de personas atrapadas en terremotos" },
    { code: "AUXI03", name: "Primeros auxilios", items: ["Botiquín", "camilla"], description: "Habilidades para la atención de primeros auxilios"},
    { code: "INUN04", name: "Control de inundaciones", items: ["Bombas", "mangueras"], description: "Habilidades para el control de inundaciones"},
    { code: "DRON05", name: "Manejo de drones", items: ["Drones", "cargador"], description: "Habilidades para el manejo de drones"},
    { code: "MAQP06", name: "Operación de maquinaria pesada", items: ["Retroexcavadora", "camión"], description: "Habilidades para la operación de maquinaria pesada"},
    { code: "ELEC07", name: "Electricidad", items: ["Cables", "focos"], description: "Habilidades para la reparación de instalaciones eléctricas"},
    { code: "MEDI08", name: "Medicina", items: ["Botiquín", "camilla"], description: "Habilidades para la atención de primeros auxilios"},
    { code: "COMU09", name: "Comunicaciones", items: ["Radios", "antenas"], description: "Habilidades para el manejo de comunicaciones"},
    { code: "TRAN10", name: "Transporte", items: ["Camión", "camioneta"], description: "Habilidades para el manejo de transporte"},
    { code: "ALIM11", name: "Alimentación", items: ["Cocina", "alimentos"], description: "Habilidades para la preparación de alimentos"},
    { code: "PSIC12", name: "Psicología", items: ["Libros", "cuadernos"], description: "Habilidades para la atención psicológica"},
    { code: "VETE13", name: "Veterinaria", items: ["Medicamentos", "jeringas"], description: "Habilidades para la atención veterinaria"}
];

skills.forEach(skill => {
    db.Skill.insert(skill);
});

// Función para buscar habilidades por código y asegurar que existen antes de insertar
function findSkill(code) {
    return db.Skill.findOne({ code: code });
}

// Insertar voluntarios en la colección 'Volunteer'
const volunteers = [
    { rut: "20000002-3", fullname: "Ana Gómez", birthday: new Date("1990-06-20"), state: 1, latitude: -39.8201, longitude: -73.2457, geom: "POINT(-73.2457 -39.8201)", abilities: [findSkill("FIRE01"), findSkill("RESC02")] },
    { rut: "20000003-4", fullname: "Carlos Rojas", birthday: new Date("1985-12-10"), state: 1, latitude: -39.8156, longitude: -73.2424, geom: "POINT(-73.2424 -39.8156)", abilities: [findSkill("AUXI03"), findSkill("MEDI08"), findSkill("DRON05")] },
    { rut: "20000004-5", fullname: "María Soto", birthday: new Date("1992-08-05"), state: 1, latitude: -39.8178, longitude: -73.2434, geom: "POINT(-73.2434 -39.8178)", abilities: [findSkill("MAQP06"), findSkill("COMU09")] },
    { rut: "20000005-6", fullname: "Pedro Torres", birthday: new Date("1987-11-30"), state: 1, latitude: -39.8184, longitude: -73.2427, geom: "POINT(-73.2427 -39.8184)", abilities: [findSkill("ELEC07"), findSkill("MEDI08"), findSkill("DRON05")] },
    { rut: "20000006-7", fullname: "Carmen Vásquez", birthday: new Date("1991-02-25"), state: 1, latitude: -39.8198, longitude: -73.2447, geom: "POINT(-73.2447 -39.8198)", abilities: [findSkill("RESC02"), findSkill("MEDI08"), findSkill("PSIC12")] },
    { rut: "20000007-8", fullname: "José González", birthday: new Date("1989-09-20"), state: 1, latitude: -39.8193, longitude: -73.2440, geom: "POINT(-73.2440 -39.8193)", abilities: [findSkill("INUN04"), findSkill("ALIM11"), findSkill("DRON05")] },
    { rut: "20000008-9", fullname: "Paula Muñoz", birthday: new Date("1986-03-15"), state: 1, latitude: -39.8202, longitude: -73.2452, geom: "POINT(-73.2452 -39.8202)", abilities: [findSkill("VETE13"), findSkill("MEDI08"), findSkill("TRAN10")] },
    { rut: "20000009-0", fullname: "Luisa Fuentes", birthday: new Date("1993-07-10"), state: 1, latitude: -39.8206, longitude: -73.2459, geom: "POINT(-73.2459 -39.8206)", abilities: [findSkill("FIRE01"), findSkill("VETE13"), findSkill("PSIC12")] }
];

volunteers.forEach(volunteer => {
    db.Volunteer.insert(volunteer);
});
