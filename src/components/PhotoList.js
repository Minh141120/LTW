import { useState, useEffect } from "react";

export const PhotoList = () => {
    const [photos, setPhotos] = useState([]);

    useEffect(() => {
        fetch("https://jsonplaceholder.typicode.com/photos")
            .then((response) => response.json())
            .then((data) => setPhotos(data))
            .catch((err) => console.log(err));
    }, []);
    return (
        <div>
            <ul>
                {photos.map((photo) => {
                    return (
                        <div>
                            <li key={photo.albumId}>{photo.title}</li>
                            <img src={photo.url}></img>
                        </div>
                    );
                })}
            </ul>
        </div>
    );
};
