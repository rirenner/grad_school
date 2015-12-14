library("ggplot2")
library("xlsx")
library("dplyr")
library("reshape")


#Change working directory
setwd("~/Documents/JHU/Data_Structures/Data_Structures_Lab4_Fall2015/src")

#Read in data
data <-data.frame(read.csv("Lab4_RunningTimeStats.csv", strip.white = TRUE))
data <- data[1:32,]

#Remove file name column
data <- data[, -1]

#Reshape data from wide to long; drop NAs that result from importing from Excel 
data <- melt(data, id.vars = c("Type", "Size"), measure.vars = c("QuickFirst_Nano", "QuickMed_Nano", "QuickR50_Nano", "QuickR100_Nano", "Heap_Nano","QuickFirst_Sec" , "QuickMed_Sec", "QuickR50_Sec","QuickR100_Sec","Heap_Sec"))
na.omit(data)



head(data)
nanoList <- c("QuickFirst_Nano", "QuickMed_Nano", "QuickR50_Nano", "QuickR100_Nano", "Heap_Nano")


for(i in 1:length(data$variable)){
  if(grepl("Nano", data$variable[i], perl=TRUE) ==TRUE){
    data$nano[i] <- 1
    data$sec[i] <- 0 } else { 
  data$nano[i] <- 0
  data$sec[i] <- 1 }
}


for(i in 1:length(nanoList))
{
   temp <- data[data$variable == nanoList[i],]
   temp <- temp[temp$nano ==1,]
   
  
  sortByType <-ggplot(data = temp, aes(Size, value), group="Type", group="variable")+
    geom_line(aes(Size, value))+
    facet_grid(.~Type)+
    ggtitle(nanoList[i])
  
  print(sortByType) 
}













