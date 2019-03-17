import { Component, OnInit } from '@angular/core';
import { DietService } from '../diet.service';
import {Router} from '@angular/router';
import {TokenStorageService} from "../token-storage.service";

@Component({
  selector: 'app-workoutmanager',
  templateUrl: './workoutmanager.component.html',
  styleUrls: ['./workoutmanager.component.css']
})



export class WorkoutmanagerComponent implements OnInit {
  constructor(private dietService:DietService,
              private router:Router,
              private tokenStorageService:TokenStorageService) {}

  exercises:Array<String>;
  chosenExercises:Array<any>;
  userWorkouts:Array<any>;
  workoutId = null;
  workoutName = '';
  object = {};
  public model:any;

  userWorkoutsPrintable:Array<any>;
  ngOnInit() {

    this.exercises = [
      'Incline pushups', 'Decline pushups','Pike pushups','Diamon pushups','Pullups','Chinups',
      'Archer Pull Ups', 'Archer Push Ups', 'FrogStand','Bar Hang','Clap Push Ups','Decline Diamond Push Ups',
      'Dips','Dip Hold','Front Lever Raises','Front Lever Hold', 'Inclined Chin Ups', 'Inclined Pull Ups',
      'Pike Push Up'
    ]


    this.dietService.getUserGoals().subscribe(data =>{
      this.userWorkouts = data.workouts;
    });


    this.chosenExercises = [];
    
  }

  sendExercise(exercise){
    var tmpExercise = {
      id: (this.chosenExercises.length)+1,
      name: exercise,
      sets: 0,
      reps: 0
    }


    this.chosenExercises.push(tmpExercise);
  }
  clearWorkout(){
    this.chosenExercises = [];
    this.workoutId = null;
    this.workoutName = '';
  }
  saveWorkout(){
    if(this.workoutId != null){
      this.userWorkouts[this.workoutId].name = this.workoutName;
      this.userWorkouts[this.workoutId].exercises = this.chosenExercises;

      this.saveWorkoutAPI(this.userWorkouts);
      this.clearWorkout();
     
    }else{
      if(this.userWorkouts.length >= 5){
        alert("Możesz utworzyć tylko 5 treningów!");
      }else{
        if(this.chosenExercises.length > 0){
            var tmp = {
              id: this.userWorkouts.length,
              name: this.workoutName,
              exercises: this.chosenExercises
            }
            this.userWorkouts.push(tmp);
            this.saveWorkoutAPI(this.userWorkouts);  
          
  
          this.clearWorkout();
        }else{
          alert("Trening jest pusty! Tu dodam modal");
        }
      }
  
    }


  }

  editWorkout(workout){
    this.chosenExercises = workout.exercises;
    this.workoutName = workout.name;
    this.workoutId = workout.id;
  }

  deleteExercise(id){
    var index = this.chosenExercises.map(a => {return a.id}).indexOf(id);
    this.chosenExercises.splice(index,1);
  }


  saveWorkoutAPI(object){
    this.dietService.saveWorkout(object).subscribe();
  }

  deleteWorkout(workout){
    this.userWorkouts.splice(workout.id,1);
    this.saveWorkoutAPI(this.userWorkouts);
  }


  signOut(){
    this.tokenStorageService.signOut();
    this.router.navigate(['']);
  }
}
