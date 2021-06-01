## Alexa Quality Assurance

**Branch name:** interfaces-classroom

**RDE workflow:** interfaces-alexa-qa

When Amazon devices are manufactured, we need to ensure that our
customers are receiving a high quality product. We do this by selecting
devices off the conveyor belt, post production, and performing rigorous
quality assurance tests. The `AlexaInspectionDeviceSelector` provides logic to
select devices for testing. A call to its `getSampleDevicePosition` method
provides the position of the device to be selected for testing.

The current implementation of the logic tests each device that is being
produced. Open the `AlexaInspectionDeviceSelector` class in the
`src/com/amazon/ata/interfaces/classroom/device/alexa/quality` package,
and review the code.

### Rethinking our sampling logic
We have decided that while it was very helpful to test each and every
device thoroughly in our early stages of production, we have improved
our processes and the quality has significantly increased. We want to
reduce our testing costs by updating our selection logic to be capable
of choosing every nth element, where n is configurable. If all goes well
we will consider moving to a random selection logic. However, we still
want to be able to swap back to our sequential logic if necessary.

With your newly acquired knowledge of interfaces, what are some ideas of
how we might update our code to accommodate these requirements? Discuss
with your table for a few minutes, and then we'll discuss as a class.

**Stop reading here** and discuss with your group about how to solve this
design problem. Propose the changes you would make to allow easily
changing the incrementing logic that `AlexaInspectionDeviceSelector`
uses.

When your group has an approach that you think makes sense, or after we
discuss approaches as a class, go ahead and continue with the
reading/activity.



### Refactoring our sampling logic

Our goal is to update `AlexaInspectionDeviceSelector` so that its
incrementer logic can easily be swapped in or out. We will do this by
depending on an interface type that defines the incrementable logic,
rather than the current `SequentialIncrementer` type it uses now. Once
we have the interface, we can then define multiple implementations and
pass whichever we prefer to `AlexaInspectionDeviceSelector`'s
constructor.

We will achieve this refactoring in multiple steps:
1. First, we will create our new interface, `Incrementable` that defines
   the increment behavior.
1. We will then update our existing `SequentialIncrementer` to implement
   ` Incrementable`.
1. Once we have done that we will be able to refactor
   `AlexaInspectionDeviceSelector` to use an `Incrementable` rather than
   a `SequentialIncrementer`. This will preserve our initial behavior
   under our new design. We'll also likely have to do some test clean up
   after this step.

After we've updated the current state to use the new design, we will
create new classes that implement `Incrementable`:
1. `FixedIncrementer`, consistently increments value by a fixed
    amount greater than one
1. `RandomSequentialIncrementer`, increments value by a random positive
    amount, where the maximum random amount is defined by the user

#### Diagramming our new design

Let's translate our new design from words and ideas to a UML diagram.
Open the file `Activity_CD.plantuml` in the
`src/com/amazon/ata/interfaces/classroom/device/alexa/quality` and work
with your group to define the new types and relationships. You should
each have an updated diagram at the end of this. We will include it in
our commit at the end of the activity.

**Doneness:** You are done with this step when:
- You have an updated version of Activity_CD.plantuml that:
  - contains the new interface, `Incrementable`
  - contains the three incrementer classes: `SequentialIncrementer`,
    `FixedIncrementer`, `RandomSequentialIncrementer`
  - depicts the "implements" relationship between these classes and
    the `Incrementable` interface

#### Create our new interface, `Incrementable`

Create the `Incrementable` interface in the
`com.amazon.ata.interfaces.increment` Java package

Our first code change is to define the 'contract' of `Incrementable`.
What behavior do we need a class to define to be incrementable?
- a way to increment the value
- a way to get the value of the incrementer

These should be the two methods we define in our new interface. Create
an interface called `Incrementable` in the
`src/com/amazon/ata/interfaces/classroom/increment` package. We will
review the interface definition as a class.

**Doneness:** You are done with this step when:
- You have created the `Incrementable` interface and everything compiles
- The interface includes a method for incrementing the value
- The interface includes a method for retrieving the current value (without
  incrementing)

#### Update our existing `SequentialIncrementer` to implement `Incrementable`

Let's update our current incrementer class, `SequentialIncrementer`, to
implement our new interface. Make sure to update the class header and
to override the required methods. IntelliJ should help you out here!

**Doneness:** You are done with this step when:
- Your `SequentialIncrementer` implements `Incrementable` and everything
  compiles

#### Refactor `AlexaInspectionDeviceSelector` to use an `Incrementable`

Now that we have an implementation of `Incrementable`, let's update our
`AlexaInspectionDeviceSelector` class to instead of depending on
`SequentialIncrementer`. There should be no references to
`SequentialIncrementer` any longer in `AlexaInspectionDeviceSelector`.
It now only uses the type, `Incrementable`.

You've made these changes, and the unit tests for
`AlexaInspectionDeviceSelector` may still compile. We've changed the type
that `AlexaInspectionDeviceSelector` expects from
`SequentialIncrementer` to `Incrementer`, but the tests in
`AlexaInspectionDeviceSelectorTest` may still compile
and work fine. How is this possible? Doesn't `AlexaInspectionDeviceSelector`
now require an `Incrementable` instead of a `SequentialIncrementer`?
Discuss with your group.

When your group has a good answer to this question, continue with
the activity.

The test code that creates a new `SequentialIncrementer` s could be
updated to take advantage of polymorphism and make the local variables
declared to be of type, `Incrementable`. Make this change!

Re-run your tests to make sure they're still compiling and passing, then
behold your newly redesigned, flexible `AlexaInspectionDeviceSelector`!

**Doneness:**
- The only code that refers to the `SequentialIncrementer` class are the
  calls to the `SequentialIncrementer` constructor in the unit tests.
- The tests compile and pass

#### COMPLETION: Create `FixedIncrementer`

Create the `FixedIncrementer` class in the
`com.amazon.ata.interfaces.increment` Java package

Let's take advantage of that flexibility we mentioned above, and work on
writing a new `Incrementable` class that we can use in our
`AlexaInspectionDeviceSelector`.

`FixedIncrementer` is an incrementer that always increases its value by
the same integer value. The incrementer object must be provided with an
increment value **in the constructor**, which will be the value that the
incrementer increases by for each increment call. The incrementer can
also optionally be provided a starting value.

Once you have finished implementing `FixedIncrementer`, write at
least two tests in `AlexaInspectionDeviceSelectorTest` that use your new
incrementer. You can follow the pattern of the existing tests in the
test class, but use `FixedIncrementer` instead of `SequentialIncrementer`.

**Doneness:** You are done with this step when:
- You have implemented `FixedIncrementer` as `Incrementable`
- You have implemented at least two unit tests of
`AlexaInspectionDeviceSelector` that use a `FixedIncrementer`
- All tests pass

If you would like to do the extension at this time, complete the next
step. If you aren't going to tackle the extension at this time, jump
down to "Commit & Push"

#### EXTENSION: Create `RandomSequentialIncrementer`

Create the `RandomSequentialIncrementer` class in the
`com.amazon.ata.interfaces.increment` Java package

`RandomSequentialIncrementer` is an incrementer that always increases
its value by a random integer amount. The incrementer must be provided
with a max increment value, so that the incrementing is bounded by 1 and
the max (1 <= random increment <= maxIncrement). The incrementer can optionally be
provided a starting value.

The
[Random](https://docs.oracle.com/javase/8/docs/api/java/util/Random.html)
class may be helpful for this implementation.

Once you have finished implementing `RandomSequentialIncrementer`, write at
least two tests in `AlexaInspectionDeviceSelectorTest` that use your new
incrementer. These might get a bit challenging, but take a look at using
a seed value with your `Random` object.

**Doneness:** You are done with this step when:
- You have implemented `RandomSequentialIncrementer` as `Incrementable`
- You have implemented at least two unit tests of
`AlexaInspectionDeviceSelector` that use a `RandomSequentialIncrementer`
- All tests pass

### Commit & Push

1. When you have the code to where you want it (at least compiling,
   ideally all tests passing), commit it. Make sure to include your UML
   diagram updates!
1. Push it to your remote branch.
1. Go back to the Alexa Quality Assurance Canvas page and paste in a
   link to your commit on Code Browser.
