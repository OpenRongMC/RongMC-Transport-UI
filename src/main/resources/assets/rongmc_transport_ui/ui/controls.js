var container = document.getElementById("controls");
var nodes = [];
function update(data) {
    var buttons = data && data.buttons ? data.buttons : [];
    while (nodes.length > buttons.length) {
        nodes.pop().remove();
    }
    buttons.forEach(function (button, index) {
        var shape = nodes[index];
        if (!shape) {
            shape = document.createElement("div");
            container.appendChild(shape);
            nodes.push(shape);
        }
        shape.className = "control " + button.state;
        shape.setAttribute("style", "left:" + button.x + "px;top:" + button.y
            + "px;width:" + button.width + "px;height:" + button.height + "px");
    });
}
oriel.on("data", update);
update(oriel.data);
