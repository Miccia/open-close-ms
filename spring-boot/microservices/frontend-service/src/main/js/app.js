'use strict';
import 'regenerator-runtime/runtime';
const React = require('react');
const ReactDOM = require('react-dom');
class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {restaurants: []};
	}

	async componentDidMount() {
    const response = await fetch("/restaurants/list/OPEN");
    const jsonData = await response.json();
    this.setState({restaurants:jsonData})
	}

	render() {
		return (
			<RestaurantList restaurants={this.state.restaurants}/>
		)
	}
}

class RestaurantList extends React.Component{
	render() {
	console.log(this.props.restaurants);
		const restaurants = Array.from(this.props.restaurants).map(restaurant =>
			<Restaurant key={restaurant.id} restaurant={restaurant}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Address</th>
					</tr>
					{restaurants}
				</tbody>
			</table>

		)
	}
}

class Restaurant extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.restaurant.name}</td>
				<td>{this.props.restaurant.description}</td>
				<td>{this.props.restaurant.address}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)