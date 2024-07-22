import {Route, Routes} from "react-router-dom";
import MainPage from "./pages/main/MainPage";
import Login from "./pages/user/Login";
import SignUp from "./pages/user/SignUp";
import MyPage from "./pages/my/MyPage";
import MyInfo from "./pages/my/MyInfo"
import MyPost from "./pages/my/MyPost";
import MyComment from "./pages/my/MyComment";
import Bookmark from "./pages/my/Bookmark";

function App(props) {
    return (
        <>
            <Routes>
                <Route path="/" element={<MainPage />} />
                <Route path="/login" element={<Login />} />
                <Route path="/signup" element={<SignUp />} />
                <Route path="/myPage" element={<MyPage />} />
                <Route path="/myInfo" element={<MyInfo />} />
                <Route path="/myPost" element={<MyPost />} />
                <Route path="/myComment" element={<MyComment />} />
                <Route path="/bookmark" element={<Bookmark />} />
            </Routes>
        </>
    );
}

export default App;