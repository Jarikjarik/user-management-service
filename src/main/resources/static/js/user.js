async function fetchJson(url) {
    const res = await fetch(url);
    if (!res.ok) throw new Error(`HTTP ${res.status}`);
    return res.json();
}

function renderMe(u) {
    const tbody = document.getElementById('meTbody');
    tbody.innerHTML = `
    <tr>
      <td class="ps-3">${u.id}</td>
      <td>${u.name}</td>
      <td>${u.lastName}</td>
      <td>${u.age}</td>
      <td>${u.email}</td>
      <td>${(u.roles || []).join(' ')}</td>
    </tr>
  `;
}

document.addEventListener('DOMContentLoaded', async () => {
    try {
        const me = await fetchJson('/api/user/me');
        renderMe(me);
    } catch (e) {
        console.error(e);
    }
});
