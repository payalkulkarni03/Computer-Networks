#include<pcap.h>
#include<stdio.h>
#include<net/ethernet.h>
#include<netinet/ip.h>    //Provides declarations for ip header
 
void process_packet(u_char *, const struct pcap_pkthdr *, const u_char *);
int tcp=0,udp=0,icmp=0,others=0,igmp=0,total=0,i,j; 
 
int main()
{
    char errbuf[PCAP_ERRBUF_SIZE];
    //Open the pcap wireshark file
	pcap_t *handle = pcap_open_offline("first.pcap", errbuf);

    //Put the device in sniff loop
    pcap_loop(handle , -1 , process_packet , NULL);
    printf("TCP : %d   UDP : %d   ICMP : %d   IGMP : %d   Others : %d   Total : %d\n", tcp , udp , icmp , igmp , others , total);
    
    return 0;   
}

void process_packet(u_char *args, const struct pcap_pkthdr *header, const u_char *buffer)
{
    int size = header->len;
    //Get the IP Header part of this packet , excluding the ethernet header
    struct iphdr *iph = (struct iphdr*)(buffer + sizeof(struct ethhdr));
    ++total;
    switch (iph->protocol) //Check the Protocol and do accordingly...
    {
        case 1:  //ICMP Protocol
            ++icmp;
            break;
         
        case 2:  //IGMP Protocol
            ++igmp;
            break;
         
        case 6:  //TCP Protocol
            ++tcp;
            break;
         
        case 17: //UDP Protocol
            ++udp;
            break;
         
        default: //Some Other Protocol like ARP etc.
            ++others;
            break;
    }
    printf("TCP : %d   UDP : %d   ICMP : %d   IGMP : %d   Others : %d   Total : %d\r", tcp , udp , icmp , igmp , others , total);
}
 /*
execute the program using 
g++ wireshark_analyzer.cpp -lpcap
./a.out*/
