const csrfToken = document.querySelector('meta[name="_csrf"]')?.getAttribute('content');
const csrfHeader = document.querySelector('meta[name="_csrf_header"]')?.getAttribute('content');

async function fetchJson(url, options = {}) {
    const opts = {...options};
    opts.headers = opts.headers || {};

    const method = (opts.method || 'GET').toUpperCase();

    if (opts.body && !opts.headers['Content-Type']) {
        opts.headers['Content-Type'] = 'application/json';
    }

    if (csrfToken && csrfHeader && method !== 'GET') {
        opts.headers[csrfHeader] = csrfToken;
    }

    const res = await fetch(url, opts);
    if (!res.ok) {
        const text = await res.text();
        throw new Error(`HTTP ${res.status}: ${text}`);
    }
    if (res.status === 204) return null;
    return res.json();
}

let allRoles = []; // [{id, name}]

function rolesToText(rolesArr) {
    return (rolesArr || []).map(r => typeof r === 'string' ? r : r.name).join(' ');
}

function renderUsersTable(users) {
    const tbody = document.getElementById('usersTbody');
    tbody.innerHTML = '';

    for (const u of users) {
        const tr = document.createElement('tr');
        tr.dataset.id = u.id;

        tr.innerHTML = `
      <td class="ps-3">${u.id}</td>
      <td>${u.name}</td>
      <td>${u.lastName}</td>
      <td>${u.age}</td>
      <td>${u.email}</td>
      <td>${rolesToText(u.roles)}</td>
      <td class="text-center">
        <button class="btn btn-info btn-sm text-white" data-action="edit" data-id="${u.id}">Edit</button>
      </td>
      <td class="text-center">
        <button class="btn btn-danger btn-sm" data-action="delete" data-id="${u.id}">Delete</button>
      </td>
    `;
        tbody.appendChild(tr);
    }
}

async function reloadUsers() {
    const users = await fetchJson('/api/users');
    renderUsersTable(users);
}

async function loadRolesOnce() {
    allRoles = await fetchJson('/api/roles');
}

function fillRolesSelect(selectEl, selectedRoles) {
    if (!selectEl) return;

    selectEl.innerHTML = '';

    const selectedSet = new Set(
        (selectedRoles || []).map(x => typeof x === 'string' ? x : x.name)
    );

    for (const r of allRoles) {
        const opt = document.createElement('option');
        opt.value = String(r.id);
        opt.textContent = r.name; // "ADMIN"/"USER"
        opt.selected = selectedSet.has(r.name);
        selectEl.appendChild(opt);
    }
}

function getSelectedRoleIds(selectEl) {
    return Array.from(selectEl.selectedOptions).map(o => Number(o.value));
}

// ----- Modals -----
const editModalEl = document.getElementById('editModal');
const editModal = editModalEl ? new bootstrap.Modal(editModalEl) : null;

const deleteModalEl = document.getElementById('deleteModal');
const deleteModal = deleteModalEl ? new bootstrap.Modal(deleteModalEl) : null;

async function openEditModal(userId) {
    const u = await fetchJson(`/api/users/${userId}`);

    document.getElementById('editId').value = u.id;
    document.getElementById('editName').value = u.name ?? '';
    document.getElementById('editLastName').value = u.lastName ?? '';
    document.getElementById('editAge').value = u.age ?? '';
    document.getElementById('editEmail').value = u.email ?? '';
    document.getElementById('editUsername').value = u.username ?? '';
    document.getElementById('editPassword').value = ''; // всегда пусто

    fillRolesSelect(document.getElementById('editRoles'), u.roles);

    editModal.show();
}

function openDeleteModal(userId) {
    document.getElementById('deleteId').value = userId;

    const tr = document.querySelector(`tr[data-id="${userId}"]`);
    const name = tr ? `${tr.children[1].textContent} ${tr.children[2].textContent}` : `user #${userId}`;
    document.getElementById('deleteLabel').textContent = name;

    deleteModal.show();
}

// ----- Handlers -----
document.addEventListener('click', (e) => {
    const btn = e.target.closest('button[data-action]');
    if (!btn) return;

    const id = Number(btn.dataset.id);
    const action = btn.dataset.action;

    if (action === 'edit') openEditModal(id).catch(console.error);
    if (action === 'delete') openDeleteModal(id);
});

document.getElementById('editForm')?.addEventListener('submit', async (e) => {
    e.preventDefault();

    const id = Number(document.getElementById('editId').value);

    const payload = {
        name: document.getElementById('editName').value.trim(),
        lastName: document.getElementById('editLastName').value.trim(),
        age: Number(document.getElementById('editAge').value),
        email: document.getElementById('editEmail').value.trim(),
        username: document.getElementById('editUsername').value.trim(),
        password: document.getElementById('editPassword').value, // может быть пустой
        rolesIds: getSelectedRoleIds(document.getElementById('editRoles'))
    };

    if (!payload.username) payload.username = null;
    if (!payload.password) payload.password = null;

    await fetchJson(`/api/users/${id}`, {
        method: 'PUT',
        body: JSON.stringify(payload)
    });

    editModal.hide();
    await reloadUsers();
});

document.getElementById('deleteForm')?.addEventListener('submit', async (e) => {
    e.preventDefault();

    const id = Number(document.getElementById('deleteId').value);

    await fetchJson(`/api/users/${id}`, {method: 'DELETE'});

    deleteModal.hide();

    const tr = document.querySelector(`tr[data-id="${id}"]`);
    if (tr) tr.remove();
});

// ----- Init -----
document.addEventListener('DOMContentLoaded', async () => {
    try {
        await loadRolesOnce();
        fillRolesSelect(document.getElementById('newRoles'), []);
        await reloadUsers();
    } catch (e) {
        console.error(e);
    }
});

function clearNewUserForm() {
    document.getElementById('newName').value = '';
    document.getElementById('newLastName').value = '';
    document.getElementById('newAge').value = '';
    document.getElementById('newEmail').value = '';
    document.getElementById('newUsername').value = '';
    document.getElementById('newPassword').value = '';
    // роли можно сбросить
    for (const opt of document.getElementById('newRoles').options) opt.selected = false;
}

document.getElementById('newUserForm')?.addEventListener('submit', async (e) => {
    e.preventDefault();

    const payload = {
        name: document.getElementById('newName').value.trim(),
        lastName: document.getElementById('newLastName').value.trim(),
        age: Number(document.getElementById('newAge').value),
        email: document.getElementById('newEmail').value.trim(),
        username: document.getElementById('newUsername').value.trim(),
        password: document.getElementById('newPassword').value,
        rolesIds: getSelectedRoleIds(document.getElementById('newRoles'))
    };

    if (!payload.username) payload.username = null;

    await fetchJson('/api/users', {
        method: 'POST',
        body: JSON.stringify(payload)
    });

    clearNewUserForm();
    await reloadUsers();

    const usersTabBtn = document.getElementById('users-tab');
    if (usersTabBtn) new bootstrap.Tab(usersTabBtn).show();
});

