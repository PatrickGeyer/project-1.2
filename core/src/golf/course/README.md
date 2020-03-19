==Course==
Inputs are functions for friction and course shape OR curves splines (phase 3), additionally a list of obstacles.
Function of this module is to keep track of the states of different objects on the course.

Example Course
g = 9.81;   // Gravitational acceleration [m/s^2]
m = 45.93;  // Mass of ball [g]
mu = 0.131; // Coefficient of friction (rolling ball)
            // Typical 0.065<=mu<=0.196
vmax = 3;   // Maximum initial ball speed [m/s]
tol = 0.02; // Distance from hole for a successful putt [m]
start = (0.0, 0.0);
goal = (0.0, 10.0);
height = -0.01*x + 0.003*x^2 + 0.04 * y;
