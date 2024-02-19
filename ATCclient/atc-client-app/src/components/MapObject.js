import { useEffect, userRef } from "react";
import api from "../api/axiosConfig"

import React from "react";
import {elementsToAdd } from App

const Elements = async (e) =>
{
    e.preventDefault()

    const response = await api.post("api/addObjects",{elements:elementsToAdd})
}
