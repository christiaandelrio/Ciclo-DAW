window.onload = () => {
	const isEnabledJava = () => {
		if (navigator.javaEnabled()) {
			return '<b>Java SI disponible</b> en esta ventana';
		} else {
			return '<b>Java No disponible</b> en esta ventana';
		}
	};

	document.getElementById('texts').innerHTML = `
        <h3 class="mt-4">Ejemplo de Ventana Nueva</h3>
        <p><b>URL completa:</b> ${window.location.href}</p>
        <p><b>Protocolo:</b> ${window.location.protocol}</p>
        <p><b>Navegador:</b> ${navigator.appCodeName}</p>
        <p>${isEnabledJava()}</p>
        <iframe
            title="iframe"
            width="800"
            height="600"
            src="https://www.google.es/">
        </iframe>
    `;
};
