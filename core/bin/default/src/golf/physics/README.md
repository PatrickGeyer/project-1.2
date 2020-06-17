==Physics==
This is the physics module of the game. It decides how objects interact with each other over time according to the necessary formulas:

=Differential Equations=
A differential equation is a way of describing the laws governing how a system changes in time [1]. A first-order equation specifies how the state 𝑝 of a system at changes in time as a function of 𝑝 itself, 𝑝̇(𝑡) = 𝑓(𝑝(𝑡)), where 𝑝̇ denotes 𝑑𝑝⁄𝑑𝑡. Most differential equations occurring in physics are second order, which means they involve second derivatives 𝑝̈ = 𝑑2 𝑝⁄𝑑𝑡2.
=Physics=
We describe the position of a ball by its coordinates 𝑝 = (𝑝𝑥, 𝑝𝑦) = (𝑥, 𝑦), with velocity 𝑣 = 𝑝̇ = (𝑣𝑥,𝑣𝑦) = (𝑥̇,𝑦̇) and acceleration 𝑎 = 𝑣̇ = 𝑝̈ = (𝑎𝑥,𝑎𝑦) = (𝑥̈,𝑦̈).
The physics of a ball moving on a hilly surface, of height 𝑧 = h(𝑥, 𝑦) is defined as follows:
We require the partial derivatives
𝜕𝑧 = h,𝑥(𝑥, 𝑦) ≔ lim h(𝑥+𝛿𝑥,𝑦)−h(𝑥,𝑦) ; 𝜕𝑧 = h,𝑦(𝑥, 𝑦) ≔ lim h(𝑥,𝑦+𝛿𝑦)−h(𝑥,𝑦) .
𝜕𝑥 𝛿𝑥→0 𝛿𝑥 𝜕𝑦 𝛿𝑦→0 𝛿𝑦
The gravitational force due to the slope is given by 𝐺 = (−𝑚𝑔h,𝑥 (𝑥, 𝑦), −𝑚𝑔h,𝑦 (𝑥, 𝑦)) ,
where 𝑚 is the mass of the ball, and 𝑔 = 9.81ms−2 the acceleration due to gravity.
The force due to friction of a moving object is:
𝐻 = −𝜇𝑚𝑔 𝑣⁄‖𝑣‖
where ‖𝑣‖ = √𝑣2 + 𝑣2 and 𝜇 is the coefficient of friction. 𝑥𝑦
The equations of motion are
𝑝̈ = 𝑎 = 𝐹⁄𝑚
where 𝐹 = 𝐹(𝑝, 𝑝̇) = 𝐹(𝑝, 𝑣) = 𝐺 + 𝐻 is the total force applied.
These forces give rise to the complete differential equation describing the motion: 𝑥̈ =−𝑔h,𝑥(𝑥,𝑦)−𝜇𝑔𝑥̇⁄√𝑥̇2 +𝑦̇2; 𝑦̈ =−𝑔h,𝑦(𝑥,𝑦)−𝜇𝑔𝑦̇⁄√𝑥̇2 +𝑦̇2.
For example, the differential equation 𝑥̈ = 2𝑥 − 𝑥̇ has a solution 𝑥(𝑡) = 3𝑒−2𝑡 since 𝑥̇(𝑡) = 𝑑 𝑥(𝑡) = −6𝑒−2𝑡; 𝑥̈(𝑡) = 𝑑2 𝑥(𝑡) = 𝑑 𝑥̇(𝑡) = 𝑑 (−6𝑒−2𝑡) = 12𝑒−2𝑡
    and
𝑑𝑡 𝑑𝑡2 𝑑𝑡 𝑑𝑡 2𝑥(𝑡) − 𝑥̇(𝑡) = 2 × (3𝑒−2𝑡) − (−6𝑒−2𝑡) = 12𝑒−2𝑡 = 𝑥̈(𝑡).
       