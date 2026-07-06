document.getElementById("detectButton").addEventListener("click", detectWebsite);

async function detectWebsite() {

    const url = document.getElementById("urlInput").value.trim();

    if (url === "") {

        showError("Please enter a website URL.");

        return;
    }

    const button = document.getElementById("detectButton");

    button.classList.add("loading");

    document.getElementById("btnLabel").innerText =
        "Analyzing Website...";

    try {

        const response = await fetch("/api/predict", {

            method: "POST",

            headers: {

                "Content-Type": "application/json"

            },

            body: JSON.stringify({

                url: url

            })

        });

        const data = await response.json();
		console.log(data);
		console.log(data.detectedFeatures);
        button.classList.remove("loading");

        document.getElementById("btnLabel").innerText =
            "🔍 Analyze Website";

        if (data.status === "INVALID_URL") {

            showError(data.message);

            return;

        }

        if (data.status === "WEBSITE_NOT_FOUND") {

            showError(data.message);

            return;

        }

        document.getElementById("result").style.display = "block";

        //-----------------------------
        // BASIC DETAILS
        //-----------------------------

        document.getElementById("url").innerText =
            data.url;

        document.getElementById("model").innerText =
            data.modelUsed;

			const scanDate = new Date(data.evaluatedAt);

			const formattedDate =
			    scanDate.toLocaleDateString("en-GB") + " " +
			    scanDate.toLocaleTimeString([], {
			        hour: "2-digit",
			        minute: "2-digit"
			    });

			document.getElementById("time").innerText =
			    formattedDate;

        //-----------------------------
        // CONFIDENCE
        //-----------------------------

        document.getElementById("confidence").innerText =
            data.confidence + "%";

        document.getElementById("confFill").style.width =
            data.confidence + "%";

        //-----------------------------
        // RISK SCORE
        //-----------------------------

		document.getElementById("riskScore").innerText =
		    data.riskScore + "%";

		const circle =
		    document.getElementById("riskCircle");

		const radius = 440;

		const offset =
		    radius - (radius * data.riskScore / 100);

		circle.style.strokeDashoffset = offset;

        const riskLevel =
            document.getElementById("riskLevel");

        if (data.riskScore >= 60) {

            riskLevel.innerText = "HIGH RISK";

			riskLevel.style.background="#c62828";
			circle.style.stroke="#ff3d3d";

        }

        else if (data.riskScore >= 30) {

            riskLevel.innerText = "MEDIUM RISK";

			riskLevel.style.background="#ff9800";
			circle.style.stroke="#ffb300";

        }

        else {

            riskLevel.innerText = "LOW RISK";

			riskLevel.style.background="#2e7d32";
			circle.style.stroke="#18b96d";

        }

        //-----------------------------
        // VERDICT
        //-----------------------------

        const badge =
            document.getElementById("verdictBadge");

        const prediction =
            document.getElementById("prediction");

        const message =
            document.getElementById("predictionMessage");

			if (data.prediction === "LEGITIMATE") {

			    badge.className = "verdict-panel safe";

			    prediction.innerText = "LEGITIMATE";

			    message.innerHTML = "✓ Safe to Use";


            document.getElementById("confFill").style.background =
                "linear-gradient(90deg,#18b96d,#4cff88)";

        }

		else {
			

		    badge.className = "verdict-panel danger";

		    prediction.innerText = "PHISHING";

		    message.innerHTML = "⚠ Unsafe Website";

            document.getElementById("confFill").style.background =
                "linear-gradient(90deg,#ff4d4d,#ff0000)";

        }

        //-----------------------------
        // FEATURES
        //-----------------------------

		const featureList = document.getElementById("featureList");

		featureList.innerHTML = "";

		if (Array.isArray(data.detectedFeatures)) {

		    data.detectedFeatures.forEach(function(feature){

		        let iconClass = "feature-danger";
		        let icon = "⚠";

		        if(feature === "No Suspicious Features"){

		            iconClass = "feature-safe";
		            icon = "✓";

		        }

		        featureList.innerHTML += `
		            <li>
		                <span class="feature-icon ${iconClass}">
		                    ${icon}
		                </span>
		                ${feature}
		            </li>`;
		    });

		} else {

		    featureList.innerHTML = `
		        <li>
		            <span class="feature-icon feature-safe">✓</span>
		            No feature information available
		        </li>`;
		}
		}

	catch (error) {

	    console.error(error);

	    button.classList.remove("loading");

	    document.getElementById("btnLabel").innerText =
	        "🔍 Analyze Website";

	    showError(error.message);

	}
}

function showError(message){

    document.getElementById("result").style.display = "none";

    document.getElementById("errorCard").style.display = "flex";

    document.getElementById("errorMessage").innerText = message;

}
function closeError(){

    document.getElementById("errorCard").style.display = "none";

}