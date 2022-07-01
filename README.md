# multithreaded-file-downloader

/**
A downloader class which performs max 5 downloads running at a time. Additional requests will be queued.

We dont need to implement the actual download logic. But just the below methods. You can assume there is a download library function that can be called which will do the actual download.
*/

/**
This is a non blocking method. It should immediately return. The actual downloading should be done in the background after this method returns. Can be called multiple times in parallel by another function. This only submits the download.
*/
void addDownload(String url)

/**
Get current status of a download. Since downloads run in the background, below are the valid states.

QUEUED - the download is added but is not actually running as there are 5 other downloads running
RUNNING - The url is being downloaded
COMPLETED - The download is completed
UNKNOWN -
*/
String getStatus(String url)

/**
Print status of all downloads added

Example:
Url Status
a.com/file1.mp4 RUNNING
b.com/file2.mp4 QUEUED
c.com/file3.mp4 RUNNING
d.com/file4.mp4 RUNNING
e.com/file9.mp4 QUEUED
f.com/file10.mp4 RUNNING
g.com/file11.mp4 RUNNING
h.com/file0.mp4 COMPLETED
i.com/file7.mp4 COMPLETED

*/
void printDownloadStatuses()

/**
Removes the url from downloading.
getStatus(url) and printDownloadStatuses() shouldnt show this url now
*/
void deleteDownload(String url)
