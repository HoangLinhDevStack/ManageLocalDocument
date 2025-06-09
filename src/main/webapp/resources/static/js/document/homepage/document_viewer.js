let currentDocId = null;
let currentDocTitle = null;

// Function to get the correct path based on user role
function getRoleBasedPath() {
    const userRole = document.querySelector('meta[name="user-role"]').getAttribute('content');
    return userRole === 'Super' ? 'super' : 'manager';
}

document.addEventListener('DOMContentLoaded', function() {
    // DEBUG: Log tất cả data-doc-id trên trang khi DOM tải xong
    document.querySelectorAll('.file-card').forEach(card => {
        console.log("DOM Loaded - Card data-doc-id:", card.getAttribute('data-doc-id'));

        // Initialize tooltip for each card
        var docTitle = card.getAttribute('data-doc-title');
        var docDescription = card.getAttribute('data-description');
        var docUploadDate = card.getAttribute('data-upload-date');
        var docUploaderName = card.getAttribute('data-uploader-name');
        var docGenreName = card.getAttribute('data-genre-name');
        var docViews = card.getAttribute('data-views');

        let tooltipHtml = '<strong>' + docTitle + '</strong><br>';

        if (docGenreName) {
            let formattedGenreName = docGenreName.replace(/[[\]]/g, '');
            tooltipHtml += 'Thể loại: ' + formattedGenreName + '<br>';
            console.log(formattedGenreName)

        }


        new bootstrap.Tooltip(card, {
            html: true,
            placement: 'bottom',
            title: tooltipHtml
        });
    });

    // Gán sự kiện chuột phải cho từng file-card
    document.querySelectorAll('.file-card').forEach(card => {
        card.addEventListener('contextmenu', function(e) {
            e.preventDefault();
            currentDocId = this.getAttribute('data-doc-id');
            currentDocTitle = this.getAttribute('data-doc-title');
            console.log("Context menu triggered. currentDocId (from clicked card):", currentDocId); // DEBUG
            const menu = document.getElementById('fileContextMenu');
            menu.style.display = 'block';
            menu.style.left = e.pageX + 'px';
            menu.style.top = e.pageY + 'px';
        });
    });

    // Ẩn menu khi click ngoài
    document.addEventListener('click', function() {
        document.getElementById('fileContextMenu').style.display = 'none';
    });

    // Đọc tài liệu
    document.getElementById('viewDocBtn').addEventListener('click', function(e) {
        e.stopPropagation();
        if (currentDocId && currentDocTitle) {
            showDocViewer(currentDocId, currentDocTitle);
        }
        document.getElementById('fileContextMenu').style.display = 'none';
    });

    // Cập nhật tài liệu
    document.getElementById('updateDocBtn').addEventListener('click', function(e) {
        e.stopPropagation();
        if (currentDocId) {
            window.location.href = contextPath + '/ManagerBook/admin/' + getRoleBasedPath() + '/update-document/' + currentDocId;
        }
        document.getElementById('fileContextMenu').style.display = 'none';
    });
});

// Hàm hiển thị modal đọc tài liệu
function showDocViewer(docId, docTitle) {
    console.log("showDocViewer called with docId:", docId, "docTitle:", docTitle);
    const modal = document.getElementById('docViewerModal');
    const iframe = document.getElementById('docViewerIframe');
    const messageDiv = document.getElementById('docViewerMessage');

    modal.querySelector('.modal-title').textContent = 'Đọc tài liệu: ' + docTitle;
    messageDiv.style.display = 'none';
    iframe.style.display = 'block';
    iframe.src = ''; // Clear previous src

    // Lấy đường dẫn file từ thuộc tính data-file-path của card đang được click
    let clickedCard = null;
    document.querySelectorAll('.file-card').forEach(card => {
        const cardDocId = card.getAttribute('data-doc-id');
        if (cardDocId && parseInt(cardDocId.trim()) === parseInt(docId)) {
            clickedCard = card;
        }
    });

    let filePath = clickedCard ? clickedCard.getAttribute('data-file-path') : '';
    
    if (filePath.toLowerCase().endsWith('.pdf')) {
        iframe.src = contextPath + '/ManagerBook/admin/' + getRoleBasedPath() + '/document/view/' + docId;
        console.log("Setting iframe src to:", iframe.src);
    } else {
        iframe.style.display = 'none';
        messageDiv.innerHTML = 'Tính năng đọc tài liệu cho loại file này sẽ được phát triển sau. ' +
            '<br><a href="' + contextPath + '/ManagerBook/admin/' + getRoleBasedPath() + '/document/view/' + docId +
            '" target="_blank" class="btn btn-primary btn-sm mt-2">Tải xuống file hoặc mở trong tab mới</a>';
        messageDiv.style.display = 'block';
    }

    var modalInstance = new bootstrap.Modal(modal);
    modalInstance.show();
} 