# User Guide

Jun You's Duke.

## Features 

### Managing todos, deadlines, and events
You can let Duke keep track of your todos, deadlines, and events.

### Saving and loading
Duke can save a set of tasks and load it when you need it.
Duke will also warn you about any unsaved tasks or tasks about to be overwritten.

### Robust error handling
Type in all the wrong commands you want - Duke won't crash on you!
Also, Duke has a debug feature to make developers' lives easier.

## Task Management Command Usage

### `todo` - Adds todo

Adds a todo to Duke's task list.

Optionally, you can specify the task to already be complete.

Example of usage: 

`todo <task description> (optional: /done)`

Expected outcome:

`Added: <task>`

### `deadline` - Adds deadline

Adds a task to Duke's task list with its deadline.

The deadline should be in the format (with 24H time): `dd/mm/yyyy hh:mm`

Example of usage: 

`deadline <task description> /by <deadline> (optional: /done)`

Expected outcome:

`Added: <task with deadline>`

### `event` - Adds event

Adds an event to Duke's task list along with its time period.

The event should be in the format (with 24H time): `dd/mm/yyyy hh:mm`

Example of usage: 

`event <event description> /at <time period> (optional: /done)`

Expected outcome:

`Added: <event with time period>`

### `list` - Lists tasks

Shows you Duke's current task list, with each task tagged with a number (the Task ID).

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`

`1. ...`

### `delete` - Deletes tasks

Deletes a task from Duke's task list. You must specify a valid task ID.

Example of usage: 

`delete <task ID>`

Expected outcome:

`Removed the task as requested: <task>`

### `find` - Find tasks

Finds the tasks from Duke's task list that includes the term you're searching for.

The tasks displayed are **not** tagged with the actual TaskID.

Example of usage: 

`find <search term>`

Expected outcome:

`For the search term '<search term>', these are the results:`
`1. ...`

### `done` - Mark tasks as complete

Marks the task with the given task ID as complete.

Example of usage: 

`done <task ID>`

Expected outcome:

`I've marked this task as complete: <task>`

## Save/Load Command Usage

### `save` - Save task list to disk

Saves Duke's current task list to disk, with a specified file name.

Example of usage:

`save <file name>`

Expected outcome:

`All files successfully saved!`

`You can now close the program.`

### `load` - Load task list from disk

Loads a specified task list on disk into Duke, replacing the current task list.
You can choose to overwrite the current task list Duke has.

Example of usage:

`load <file name> (optional: /overwrite)`

Expected outcome:

`Successfully loaded from save file!`

### `show-saves` - Show all saved task lists

Shows all saved task lists.

Example of usage:

`show-saves`

Expected outcome:

`To load, type 'load <filename>'.`

`Here's all your saves:`

## Utility Command Usage

### `debug` - Enter debug mode

Toggles debug mode. When debug mode is enabled, you can see
command composition and detailed stack traces.

Example of usage:

`debug`

Expected outcome:

`Toggled debug mode to: <debug state>`

### `bye` - Exit Duke

Exits Duke. If tasks have not been saved, Duke will alert you.
You can choose to force-quit Duke with an additional argument.

Example of usage:

`bye (optional: force-quit)`

Expected outcome:

`Bye. Hope to see you soon!`