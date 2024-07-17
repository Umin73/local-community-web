import React from "react";
import {
    BrowserRouter,
    Routes,
    Route
} from "react-router-dom";

// Pages
import MainPage from "./component/page/MainPage";

function App(props) {
    return (
        <BrowserRouter>
            <Routes>
                <Route index element = {<MainPage />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;