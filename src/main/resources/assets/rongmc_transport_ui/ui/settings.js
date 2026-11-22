var flags = ["announcements", "speech", "translucency", "language", "rail", "font", "shadows"];
var values = ["texture", "oscillation"];
var controls = {};
var labels = {};
var section = document.getElementById("options");

function row(key, control) {
    var element = document.createElement("div");
    element.className = "row";
    var label = document.createElement("label");
    label.setAttribute("for", key);
    control.id = key;
    element.appendChild(label);
    element.appendChild(control);
    section.appendChild(element);
    labels[key] = label;
    controls[key] = control;
}

flags.forEach(function (key) {
    var button = document.createElement("button");
    button.addEventListener("click", function () { oriel.send("toggle", key); });
    row(key, button);
});

values.forEach(function (key) {
    var container = document.createElement("div");
    var input = document.createElement("input");
    input.type = "range";
    input.min = "0";
    input.max = key === "texture" ? "7" : "15";
    input.step = "1";
    input.addEventListener("change", function () {
        var payload = {};
        payload[key] = Number(input.value);
        oriel.send("numbers", payload);
    });
    row(key, container);
    container.removeAttribute("id");
    input.id = key;
    container.appendChild(input);
    controls[key] = input;
});

var done = document.getElementById("back");
document.getElementById("style").addEventListener("click", function () { oriel.send("style", null); });
document.getElementById("support").addEventListener("click", function () { oriel.send("support", null); });
done.addEventListener("click", function () { oriel.send("back", null); });

function update(data) {
    if (!data || !data.title) return;
    document.getElementById("title").textContent = data.title;
    done.textContent = data.back;
    document.getElementById("style").textContent = data.style;
    document.getElementById("support").textContent = data.support;
    flags.concat(values).forEach(function (key) {
        labels[key].textContent = data[key + "Label"];
    });
    flags.forEach(function (key) {
        controls[key].textContent = key === "language" ? data[key] : data[key] ? data.on : data.off;
    });
    controls.rail.disabled = !data.railAvailable;
    values.forEach(function (key) { controls[key].value = String(data[key]); });
}

oriel.on("data", update);
update(oriel.data);
