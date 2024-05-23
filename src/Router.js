import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./css/App.css";
import Bookmark from "./pages/Bookmark";
import MyInfo from "./pages/MyInfo";
import MyPost from "./pages/MyPost";
import MyComment from "./pages/MyComment";
import Header from "./components/Header";

export default function Router() {
  return (
    <>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/myComment" element={<MyComment />} />
          <Route path="/myPost" element={<MyPost />} />
          <Route path="/bookmark" element={<Bookmark />} />
          <Route path="/myInfo" element={<MyInfo />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}
