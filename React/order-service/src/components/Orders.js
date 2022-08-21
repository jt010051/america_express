import React from 'react'
import { useState } from 'react';
import axios from 'axios';
import { useEffect } from 'react';
import up_arrow from '../images/up_arrow.png'
import down_arrow from '../images/down_arrow.png'

const url = "http://localhost:8080/api/auth/products";
const url2 = "http://localhost:8080/api/auth/orders";
function ButtonIncrement(props) {

    return (
        <button style={{ marginLeft: '.5rem' }} onClick={props.onClickFunc}>
            +1
        </button>
    )
}
function ButtonDecrement(props) {

    return (
        <button style={{ marginLeft: '.5rem' }} onClick={props.onClickFunc}>
            -1
        </button>
    )
}
function Display(props) {
    return (
        <label style={{ marginLeft: '.5rem' }} >{props.message}</label>
    )
}

const Orders = () => {
    const [products, setProducts] = useState([])
    const [order, setOrder] = useState([])


    const [counter, setCounter] = useState(1);
    const incrementCounter = () => setCounter(counter + 1);
    let decrementCounter = () => setCounter(counter - 1);
    if (counter <= 0) {
        decrementCounter = () => setCounter(1);
    }

    const fetchProducts = () => {


        axios.get(url).then(res => {
            setProducts(res.data)

        });
    };

    const fetchOrders = () => {


        axios.get(url2).then(res => {
            setOrder(res.data)

        });
    };
    useEffect(() => {
        fetchProducts();
        fetchOrders();

    }, [])

    const displayProducts = products
        .map((product) => {
            if (counter > product.quantity) {
                setCounter(product.quantity)
            }
            const orderSumary = () => {

                // let fruit = document.getElementById({product.f})
                console.log(document.getElementById("fruit"));
                document.getElementById("Orders").innerHTML = "Order Summary";
                // document.getElementById("Fruit").innerHTML = fruit;
                document.getElementById("Quantity").innerHTML = counter;
            }
            return (
                <React.Fragment key={product.id}>

                    <tbody>
                        <tr>
                            <td id="fruit">{product.fruit}</td>


                            <td>$ {product.price}</td>
                            <td>Quantity: </td>
                            <div>
                                <ButtonDecrement onClickFunc={decrementCounter} />

                                <Display message={counter} />
                                <ButtonIncrement onClickFunc={incrementCounter} />
                            </div>
                        </tr>
                    </tbody>
                    <button id="btn" onClick={orderSumary}>Place Order</button>
                </React.Fragment>
            )
        });

    return (<>

        <h1>Shopping Cart</h1>

        <div className="shopping-cart">




            {displayProducts}


        </div>
        <div id="Orders"></div>
        <div id="Quantity"></div>
        <div id="Fruit"></div>


    </>
    )
}

export default Orders