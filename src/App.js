import logo from "./logo.svg";
import "./App.css";
import Login from "./components/Login";
import LoginClass from "./components/LoginClass";
import LoginHeader from "./components/LoginHeader";
import Footer from "./components/Footer";
import { PostList } from "./components/PostList";
import Books from "./components/Books";
function App() {
    return (
        <div className="App">
            <LoginHeader />
            <header className="App-header">
                <p>
                    <Books />
                </p>
            </header>
            <Footer />
        </div>
    );
}

export default App;
