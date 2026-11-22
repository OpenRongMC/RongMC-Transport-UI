var label = document.getElementById("label");
var watermark = document.getElementById("watermark");
var done = document.getElementById("back");

function update(data) {
    if (!data || !data.label) return;
    label.textContent = data.label;
    watermark.textContent = data.value;
    done.textContent = data.done;
}

watermark.addEventListener("click", function () { oriel.send("toggle", null); });
done.addEventListener("click", function () { oriel.send("back", null); });
oriel.on("data", update);
update(oriel.data);
