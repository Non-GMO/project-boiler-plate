// GMO_Search
// searches stuff

#include<iostream>
#include<fstream>
#include<string>
using namespace std;

int WithUPC(string UPC)
{
	// takes int UPC and scans it against UPCs in the database
	// 1 if found in database
	// 0 if not found 
	// -1 if error

		string line;
		bool find = false;
		ifstream infile("Complete list of UPC.txt");
		if (infile.is_open())
		{
			// cout << "enter UPC";
			cout << "opened" << endl;
			// start reading in UPC codes
			while (getline(infile, line))
			{
				// as each UPC is read, it is compared to the target
				if (UPC == line)
				{
					cout << "match" << endl;
					find = true;
					infile.close();
					return 1;	// 1 is found in the database
				}
			}
			// if the list is finished and the target is not found, return no match
			if (infile.eof() && (find != true))
			{
				cout << "no match" << endl;
				infile.close();
				return 0;	// 0 is not found in the database
			}
			cout << endl << "complete" << endl;
		
		}
		else
		{
			cout << "Unable to open file";
			return -1;		// -1 is an error
		}
	}

int WithoutUPC(string search)
{
	// takes search string and compares it to items in the database
	// 1 if found in database
	// 0 if not found
	// -1 if error

	return 0; // placeholder, remove when actual work is done
}




int main()
{
	string target;
	cout << "enter target UPC" << endl;
	cin >> target;
	WithUPC(target);
	if (WithUPC(target) == 1)	// UPC was found, return product info
	{
		// in another castle...
	}

	system("pause");
}
