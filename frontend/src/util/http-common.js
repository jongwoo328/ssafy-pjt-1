import axios from "axios";

export default axios.create({
  baseURL: "http://192.168.219.176:9999/connector",
  headers: {
    "Content-type": "application/json",
  },
});