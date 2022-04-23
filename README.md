# CommonEdit
The members of Yixu organization have had enough of making wheels. Now they plan to collect the wheels they have made and organize them into a library called CommonEdit.

This project includes the source code of all open source CE projects.
CE project is a basic auxiliary Library Based on standard C++ in algorithm and QT in window.
It can help users avoid building some functions, objects or other containers that sound simple but actually need to spend energy to implement.

All C++CE projects are located under the namespace CE.

The correct way to use CE projects is to include the header file commonedit.h first.
Generally, the file will help you prepare the remaining inclusion relationships for your direct use.
Only when you haven't found what you want after including the file commonedit.h can you include other files in the CE project.

Please note that the QT version used in the construction of the CE project is 5.11, which generally means that most of the contents are compatible with Qt5(especially qt5.9 ~qt5.15).
After a rough review of the introduction of QT6 and the changes pointed out by other developers, we believe that most of the contents involved in the CE project should also be compatible with QT6.
If you find incompatibility, please report to the CE project team.

Because a member of Yixu organization is deeply trapped in the quagmire of OpenCV, this project also has the wheel of OpenCV.

Since the CE library comes from different developers, when the codes first appear in this project, they are likely to be inconsistent with the mainstream style, and even do not use the standardized namespace and macro system. These format flaws will be corrected in the future.

PyCE is not updated synchronously with C++CE, but files with the same name will provide a unified use experience as much as possible
