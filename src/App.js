import "./css/App.css";
import Sidebar from "./components/Sidebar";
import Router from "./Router";

function App() {
  return (
    <div>
      <div className="root-wrap">
        <Router />
      </div>
      <div className="side-wrap">
        <Sidebar />
      </div>
    </div>
  );
}

export default App;
